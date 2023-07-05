package Classes;

import java.awt.*;

public class Colors {
    private final Color foreground_;
    private final Color background_;

    public Colors() {
        this.foreground_ = new Color(187, 189, 194);
        this.background_ = new Color(30, 31, 34);
    }

    public Color getForeground_() {
        return foreground_;
    }

    public Color getBackground_() {
        return background_;
    }
}