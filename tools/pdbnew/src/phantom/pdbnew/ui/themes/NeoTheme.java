package phantom.pdbnew.ui.themes;

import phantom.pdbnew.ui.system.UITheme;

import java.awt.*;

public class NeoTheme extends UITheme {
    public NeoTheme() {
        color_notification_bg_inf=Color.WHITE  ;
        color_notification_fg_inf=Color.BLUE   ;
        color_notification_bg_err=Color.WHITE  ;
        color_notification_fg_err=Color.RED    ;
        color_notification_bg_wrn=Color.WHITE  ;
        color_notification_fg_wrn=Color.YELLOW ;
        color_notification_bg_scs=Color.WHITE  ;
        color_notification_fg_scs=Color.GREEN  ;
        url_icon_resource_inf    ="error"      ;
        url_icon_resource_err    ="info"       ;
        url_icon_resource_wrn    ="warning"    ;
        url_icon_resource_scs    ="success"    ;
        url_icon_resource_file   ="file"       ;
    }
}
