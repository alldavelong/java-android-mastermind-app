package fhku.appprojektmastermind;

import java.util.List;

import fhku.appprojektmastermind.color.PresetColorBall;
import fhku.appprojektmastermind.container.ColorGuess;
import fhku.appprojektmastermind.container.ColorRepertoire;

public class MastermindGame {
    private final int COLOR_PATTERN_LENGTH;
    private final int ALLOWED_COLOR_GUESSES;

    private final List<ColorGuess> COLOR_GUESS_LIST;
    private final ColorRepertoire COLOR_REPERTOIRE;

    private int activeColorGuessIndex = 0;

    public MastermindGame(int color_pattern_length, int allowed_color_guesses) {
        COLOR_PATTERN_LENGTH = color_pattern_length;
        ALLOWED_COLOR_GUESSES = allowed_color_guesses;

        COLOR_GUESS_LIST = ColorGuess.emptyGuessList(ALLOWED_COLOR_GUESSES, COLOR_PATTERN_LENGTH);
        setupColorGuessListForTesting();

        // set up a ColorRepertoire containing all available PresetColorBalls
        COLOR_REPERTOIRE = new ColorRepertoire(PresetColorBall.values());

        COLOR_GUESS_LIST.get(0).setActive(true);
    }

    public List<ColorGuess> getColorGuesses() {
        return COLOR_GUESS_LIST;
    }

    public ColorRepertoire getColorRepertoire() {
        return COLOR_REPERTOIRE;
    }

    private void playNextGuess() {
        COLOR_GUESS_LIST.get(activeColorGuessIndex).setActive(false);
        COLOR_GUESS_LIST.get(++activeColorGuessIndex).setActive(true);

        //TODO: default ColorGuess = inactive and grayed out
        //TODO: activate next ColorGuess
        //TODO: deactivate previous ColorGuess (no more interaction, but not grayed out)
    }

    public void validate(ColorGuess colorGuess) {
        COLOR_GUESS_LIST.get(activeColorGuessIndex).setColorBalls(colorGuess.getColorBalls());
        playNextGuess();
    }


    private void setupColorGuessListForTesting() {
        COLOR_GUESS_LIST.set(0, new ColorGuess(List.of(
                PresetColorBall.RED.getBall(),
                PresetColorBall.BLUE.getBall(),
                PresetColorBall.GREEN.getBall(),
                PresetColorBall.BROWN.getBall()
        )));
        /*COLOR_GUESS_LIST.set(1, new ColorGuess(List.of(
                PresetColorBall.BROWN.getBall(),
                PresetColorBall.RED.getBall(),
                PresetColorBall.GREEN.getBall(),
                PresetColorBall.YELLOW.getBall()
        )));
        COLOR_GUESS_LIST.set(2, new ColorGuess(List.of(
                PresetColorBall.YELLOW.getBall(),
                new EmptyColorBall(),
                PresetColorBall.RED.getBall(),
                PresetColorBall.BLUE.getBall()
        )));*/
    }
}
