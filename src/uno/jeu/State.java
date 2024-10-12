package uno.jeu;

/**
 * L'état actuel du jeu. Prends 4 états différents:
 * <li>{@code GameOver} - Le jeu est terminé.</li>
 * <li>{@code Bot} - Un bot joue.</li>
 * <li>{@code Player} - Un joueur joue.</li>
 * <li>{@code Idle} - Plus rien à faire.</li>
 */
public enum State {
    GameOver,
    Bot,
    Player,
    Idle
}
