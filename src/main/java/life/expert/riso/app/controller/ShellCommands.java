package life.expert.riso.app.controller;


import static life.expert.riso.app.OutputHelper.FIGURE_DEFAULT_CHARACTER;
import static life.expert.riso.app.OutputHelper.MAX_CANVAS_SIZE;
import static life.expert.riso.app.OutputHelper.MAX_SCREEN_SIZE;
import static life.expert.riso.app.OutputHelper.MIN_CANVAS_SIZE;
import static life.expert.riso.app.OutputHelper.MIN_SCREEN_SIZE;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import life.expert.riso.app.OutputHelper;
import life.expert.riso.domain.service.CanvasService;
import life.expert.riso.domain.service.dto.CanvasDataTransferObject;
import life.expert.riso.domain.service.dto.FillDataTransferObject;
import life.expert.riso.domain.service.dto.LineDataTransferObject;
import life.expert.riso.domain.service.dto.RectangleDataTransferObject;
import life.expert.riso.domain.service.dto.ResultDataTransferObject;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;


/**
 * <pre>
 * The class contains commands called by the shell.
 *
 * Preconditions: Spring shell instantiated
 * Postconditions: Canvas with added figures
 * Side effects: console/file output
 * Tread safety: Not thread-safe
 * </pre>
 */
@Slf4j
@ShellComponent
@ShellCommandGroup("Drawing Commands")
@Getter
public final class ShellCommands {

  private final OutputHelper shellHelper;

  private final CanvasService canvasService;

  private String currentCanvas;

  private boolean isCanvasAvialable = false;

  /**
   * Sets current canvas for user drawing.
   *
   * @param currentCanvas the current canvas
   */
  public void setCurrentCanvas(String currentCanvas) {
    this.currentCanvas = currentCanvas;
    isCanvasAvialable = true;
  }

  /**
   * Instantiates a new Shell commands.
   *
   * @param shellHelper   the shell helper
   * @param canvasService the canvas service
   */
  @Autowired
  public ShellCommands(@Lazy OutputHelper shellHelper,
      @Lazy CanvasService canvasService) {
    this.shellHelper = shellHelper;
    this.canvasService = canvasService;

  }

  /**
   * Create canvas shell command.
   *
   * @param width  the width
   * @param height the height
   */
  @ShellMethod(key = "C", value = "Create a new canvas of width w and height h.")
  public void createCanvas(@Min(MIN_SCREEN_SIZE) @Max(MAX_SCREEN_SIZE) int width,
      @Min(MIN_SCREEN_SIZE) @Max(MAX_SCREEN_SIZE) int height) {

    getCanvasService().createCanvas(new CanvasDataTransferObject(width, height))
        .doOnNext(c -> setCurrentCanvas(c.getCanvasId()))
        .map(ResultDataTransferObject::getScreen)
        .subscribe(shellHelper::print, shellHelper::printAtError);
  }

  /**
   * New line shell command.
   *
   * @param firstPointX  the first point x
   * @param firstPointY  the first point y
   * @param secondPointX the second point x
   * @param secondPointY the second point y
   */
  @ShellMethod(key = "L", value = "Create a new line from (x1,y1) to (x2,y2). Currently only\n horizontal or vertical lines are supported. Horizontal and vertical lines\n will be drawn using the 'x' character.")
  @ShellMethodAvailability("availabilityCheck")
  public void newLine(@Min(MIN_CANVAS_SIZE) @Max(MAX_CANVAS_SIZE) int firstPointX,
      @Min(MIN_CANVAS_SIZE) @Max(MAX_CANVAS_SIZE) int firstPointY,
      @Min(MIN_CANVAS_SIZE) @Max(MAX_CANVAS_SIZE) int secondPointX,
      @Min(MIN_CANVAS_SIZE) @Max(MAX_CANVAS_SIZE) int secondPointY) {
    var l = new LineDataTransferObject();
    l.setCanvasId(getCurrentCanvas());
    l.setX0(firstPointX);
    l.setY0(firstPointY);
    l.setX1(secondPointX);
    l.setY1(secondPointY);
    l.setCharacter(FIGURE_DEFAULT_CHARACTER);

    getCanvasService().newLine(l)
        .map(ResultDataTransferObject::getScreen)
        .subscribe(shellHelper::print, shellHelper::printAtError);
  }

  /**
   * New rectangle shell command.
   *
   * @param upperLeftCornerX  the upper left corner x
   * @param upperLeftCornerY  the upper left corner y
   * @param lowerRightCornerX the lower right corner x
   * @param lowerRightCornerY the lower right corner y
   */
  @ShellMethod(key = "R", value = "Create a new rectangle, whose upper left corner is (x1,y1) and\n lower right corner is (x2,y2). Horizontal and vertical lines will be drawn\n using the 'x' character.")
  @ShellMethodAvailability("availabilityCheck")
  public void newRectangle(@Min(MIN_CANVAS_SIZE) @Max(MAX_CANVAS_SIZE) int upperLeftCornerX,
      @Min(MIN_CANVAS_SIZE) @Max(MAX_CANVAS_SIZE) int upperLeftCornerY,
      @Min(MIN_CANVAS_SIZE) @Max(MAX_CANVAS_SIZE) int lowerRightCornerX,
      @Min(MIN_CANVAS_SIZE) @Max(MAX_CANVAS_SIZE) int lowerRightCornerY) {
    var l = new RectangleDataTransferObject();
    l.setCanvasId(getCurrentCanvas());
    l.setX0(upperLeftCornerX);
    l.setY0(upperLeftCornerY);
    l.setX1(lowerRightCornerX);
    l.setY1(lowerRightCornerY);
    l.setCharacter(FIGURE_DEFAULT_CHARACTER);

    getCanvasService().newRectangle(l)
        .map(ResultDataTransferObject::getScreen)
        .subscribe(shellHelper::print, shellHelper::printAtError);
  }

  /**
   * Fill area shell command.
   *
   * @param fillFromStartPointX the fill from start point x
   * @param fillFromStartPointY the fill from start point y
   * @param colour              the colour
   */
  @ShellMethod(key = "B", value = "Fill the entire area connected to (x,y) with \"colour\" c. The\n behavior of this is the same as that of the \"bucket fill\" tool in paint\n programs.")
  @ShellMethodAvailability("availabilityCheck")
  public void fillArea(@Min(MIN_CANVAS_SIZE) @Max(MAX_CANVAS_SIZE) int fillFromStartPointX,
      @Min(MIN_CANVAS_SIZE) @Max(MAX_CANVAS_SIZE) int fillFromStartPointY,
      @Pattern(regexp = "[a-zA-Z_0-9 ]{1}") String colour) {
    char c = colour.charAt(0);

    var l = new FillDataTransferObject();
    l.setCanvasId(getCurrentCanvas());
    l.setX(fillFromStartPointX);
    l.setY(fillFromStartPointY);
    l.setCharacter(c);

    getCanvasService().newFill(l)
        .map(ResultDataTransferObject::getScreen)
        .subscribe(shellHelper::print, shellHelper::printAtError);
  }

  /**
   * drawing commands cannot be accessed if no drawing canvas is created
   *
   * @return the drawing commands availability
   */
  public Availability availabilityCheck() {
    return isCanvasAvialable() ? Availability.available()
        : Availability.unavailable("you should create a new canvas.");
  }

}
