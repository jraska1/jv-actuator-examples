package cz.dsw.actuator_examples.example02.event;

import org.springframework.context.ApplicationEvent;

public class CustomEvent extends ApplicationEvent {

    public CustomEvent(Object source) {
        super(source);
    }

    public String getEvent() {
        String name = "";
        Class<?> cls = this.getClass();
        do {
            name = (name.length() > 0) ? cls.getSimpleName() + "." + name : cls.getSimpleName();
            cls = cls.getSuperclass();
        } while (cls != ApplicationEvent.class);
        return name;
    }
}
