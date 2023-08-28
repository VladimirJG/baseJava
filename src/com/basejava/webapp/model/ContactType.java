package com.basejava.webapp.model;

public enum ContactType {
    TELEPHONE("Telephone"),
    SKYPE("Skype") {
        @Override
        public String toHtml0(String value) {
            return getName() + ": " + toLink("skype:" + value, value);
        }
    },
    MAIL("Mail") {
        @Override
        public String toHtml0(String value) {
            return getName() + ": " + toLink("mailto:" + value, value);
        }
    },
    LINKEDIN("Linkedin") {
        @Override
        public String toHtml0(String value) {
            return toLink(value);
        }
    },
    GITHUB("Github") {
        @Override
        public String toHtml0(String value) {
            return toLink(value);
        }
    },
    STACKOVERFLOW("Stackoverflow") {
        @Override
        public String toHtml0(String value) {
            return toLink(value);
        }
    },
    HOMEPAGE("Homepage") {
        @Override
        public String toHtml0(String value) {
            return toLink(value);
        }
    };
    private final String name;

    public String getName() {
        return name;
    }

    ContactType(String name) {
        this.name = name;
    }

    protected String toHtml0(String value) {
        return name + ": " + value;
    }

    public String toHtml(String value) {
        return (value == null) ? "" : toHtml0(value);
    }

    public String toLink(String href) {
        return toLink(href, name);
    }

    public static String toLink(String href, String name) {
        return "<a href='" + href + "'>" + name + "</a>";
    }
}
