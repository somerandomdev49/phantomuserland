package phantom.pdbnew.ui.system;

import java.awt.*;

import org.json.*;

public abstract class UITheme {

    protected String name;
    protected String id;
    protected String description;

    // type_category_mod_name

    // types: color, text, url;

    // color:  java.awt.Color;
    // text:   java.lang.String;
    // url:    java.lang.String;

    // color mod:  bg, fg;
    // text mod:   bold, italic, underline;
    // url mod:    resource, full;

    // categories: notification, main, icon;

    // names:

    public Color color_notification_bg_inf, color_notification_fg_inf;
    public Color color_notification_bg_err, color_notification_fg_err;
    public Color color_notification_bg_wrn, color_notification_fg_wrn;
    public Color color_notification_bg_scs, color_notification_fg_scs;
    public Color color_main_bg;
    public Color color_main_fg;

    public String url_icon_resource_inf   ;
    public String url_icon_resource_err   ;
    public String url_icon_resource_wrn   ;
    public String url_icon_resource_scs   ;
    public String url_icon_resource_file  ;

    public String THEME_RESOURCE_PATH;

    public Color getColor_notification_bg_inf() {
        return color_notification_bg_inf;
    }

    public Color getColor_notification_fg_inf() {
        return color_notification_fg_inf;
    }

    public Color getColor_notification_bg_err() {
        return color_notification_bg_err;
    }

    public Color getColor_notification_fg_err() {
        return color_notification_fg_err;
    }

    public Color getColor_notification_bg_wrn() {
        return color_notification_bg_wrn;
    }

    public Color getColor_notification_fg_wrn() {
        return color_notification_fg_wrn;
    }

    public Color getColor_notification_bg_scs() {
        return color_notification_bg_scs;
    }

    public Color getColor_notification_fg_scs() {
        return color_notification_fg_scs;
    }

    public String getUrl_icon_resource_inf() {
        return THEME_RESOURCE_PATH + url_icon_resource_inf + ".png";
    }

    public String getUrl_icon_resource_err() {
        return THEME_RESOURCE_PATH + url_icon_resource_err + ".png";
    }

    public String getUrl_icon_resource_wrn() {
        return THEME_RESOURCE_PATH + url_icon_resource_wrn + ".png";
    }

    public String getUrl_icon_resource_scs() {
        return THEME_RESOURCE_PATH + url_icon_resource_scs + ".png";
    }

    public String getUrl_icon_resource_file() {
        return THEME_RESOURCE_PATH + url_icon_resource_file + ".png";
    }

    private static Color colorFromJSON(JSONObject obj) {
        return new Color(obj.getInt("r"), obj.getInt("g"), obj.getInt("b"));
    }

    public static UITheme fromJSON(String path, String meta, String json) {
        JSONObject jsonMeta = new JSONObject(meta);
        JSONObject jsonTheme = new JSONObject(json);
        UITheme theme = new UITheme() {};
        theme.THEME_RESOURCE_PATH = path;
        theme.name = jsonMeta.getString("name");
        theme.id = jsonMeta.getString("id");
        theme.description = jsonMeta.getString("description");
        //theme.color_notification_bg_inf = colorFromJSON(jsonTheme.getJSONObject("color_notification_bg_inf"))
        theme.color_notification_bg_inf = colorFromJSON(jsonTheme.getJSONObject("color_notification_bg_inf"));
        theme.color_notification_fg_inf = colorFromJSON(jsonTheme.getJSONObject("color_notification_fg_inf"));
        theme.color_notification_bg_err = colorFromJSON(jsonTheme.getJSONObject("color_notification_bg_err"));
        theme.color_notification_fg_err = colorFromJSON(jsonTheme.getJSONObject("color_notification_fg_err"));
        theme.color_notification_bg_wrn = colorFromJSON(jsonTheme.getJSONObject("color_notification_bg_wrn"));
        theme.color_notification_fg_wrn = colorFromJSON(jsonTheme.getJSONObject("color_notification_fg_wrn"));
        theme.color_notification_bg_scs = colorFromJSON(jsonTheme.getJSONObject("color_notification_bg_scs"));
        theme.color_notification_fg_scs = colorFromJSON(jsonTheme.getJSONObject("color_notification_fg_scs"));
        theme.url_icon_resource_inf     = (jsonTheme.getString("url_icon_resource_inf"   ));
        theme.url_icon_resource_err     = (jsonTheme.getString("url_icon_resource_err"   ));
        theme.url_icon_resource_wrn     = (jsonTheme.getString("url_icon_resource_wrn"   ));
        theme.url_icon_resource_scs     = (jsonTheme.getString("url_icon_resource_scs"   ));
        theme.url_icon_resource_file    = (jsonTheme.getString("url_icon_resource_file"  ));

        System.out.println(theme.toString());

        return theme;
    }
    @Override
    public String toString() {
        return name;
    }
}
