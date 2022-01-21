package com.jsheets.events;

/**
 * A fireabl event that carries only its {@code sender}.
 */
public class ActionEvent<TSender> extends Event<EventArgs<TSender>> {}
