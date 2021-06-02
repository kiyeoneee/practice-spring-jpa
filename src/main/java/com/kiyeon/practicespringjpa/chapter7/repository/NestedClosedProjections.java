package com.kiyeon.practicespringjpa.chapter7.repository;

public interface NestedClosedProjections {
    String getUsername();
    TeamInfo getTeam();

    interface TeamInfo {
        String getName();
    }
}
