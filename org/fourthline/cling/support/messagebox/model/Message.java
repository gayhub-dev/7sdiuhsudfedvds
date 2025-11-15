package org.fourthline.cling.support.messagebox.model;

import android.support.v7.widget.ActivityChooserView;
import java.util.Random;
import org.fourthline.cling.support.messagebox.parser.MessageDOM;
import org.fourthline.cling.support.messagebox.parser.MessageDOMParser;
import org.fourthline.cling.support.messagebox.parser.MessageElement;
import org.seamless.xml.AbstractC1723b;
import org.seamless.xml.C1727f;
import org.w3c.dom.DOMException;

/* loaded from: classes.dex */
public abstract class Message implements ElementAppender {
    private final Category category;
    private DisplayType displayType;

    /* renamed from: id */
    private final int f4885id;
    public final Random randomGenerator;

    public enum Category {
        SMS("SMS"),
        INCOMING_CALL("Incoming Call"),
        SCHEDULE_REMINDER("Schedule Reminder");

        public String text;

        Category(String str) {
            this.text = str;
        }
    }

    public enum DisplayType {
        MINIMUM("Minimum"),
        MAXIMUM("Maximum");

        public String text;

        DisplayType(String str) {
            this.text = str;
        }
    }

    public Message(Category category, DisplayType displayType) {
        this(0, category, displayType);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.f4885id == ((Message) obj).f4885id;
    }

    public Category getCategory() {
        return this.category;
    }

    public DisplayType getDisplayType() {
        return this.displayType;
    }

    public int getId() {
        return this.f4885id;
    }

    public int hashCode() {
        return this.f4885id;
    }

    public String toString() throws DOMException {
        try {
            MessageDOMParser messageDOMParser = new MessageDOMParser();
            MessageDOM messageDOM = (MessageDOM) messageDOMParser.createDocument();
            MessageElement messageElementCreateRoot = messageDOM.createRoot(messageDOMParser.createXPath(), "Message");
            messageElementCreateRoot.createChild("Category").setContent(getCategory().text);
            messageElementCreateRoot.createChild("DisplayType").setContent(getDisplayType().text);
            appendMessageElements(messageElementCreateRoot);
            return messageDOMParser.print((AbstractC1723b) messageDOM, 0, false).replaceAll("<Message xmlns=\"urn:samsung-com:messagebox-1-0\">", "").replaceAll("</Message>", "");
        } catch (C1727f e7) {
            throw new RuntimeException(e7);
        }
    }

    public Message(int i7, Category category, DisplayType displayType) {
        Random random = new Random();
        this.randomGenerator = random;
        this.f4885id = i7 == 0 ? random.nextInt(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED) : i7;
        this.category = category;
        this.displayType = displayType;
    }
}
