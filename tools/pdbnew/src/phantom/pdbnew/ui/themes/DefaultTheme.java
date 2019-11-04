package phantom.pdbnew.ui.themes;

import phantom.pdbnew.ui.UITheme;

import java.awt.*;

public class DefaultTheme extends UITheme {
    public DefaultTheme() {
        color_notification_bg_inf=Color.WHITE  ;
        color_notification_fg_inf=Color.BLUE   ;
        color_notification_bg_err=Color.WHITE  ;
        color_notification_fg_err=Color.RED    ;
        color_notification_bg_wrn=Color.WHITE  ;
        color_notification_fg_wrn=Color.YELLOW ;
        color_notification_bg_scs=Color.WHITE  ;
        color_notification_fg_scs=Color.GREEN  ;
        url_icon_resource_inf="info"           ;
        url_icon_resource_err="error"          ;
        url_icon_resource_wrn="warning"        ;
        url_icon_resource_scs="success"        ;
        url_icon_resource_add="add"            ;
    }
}
