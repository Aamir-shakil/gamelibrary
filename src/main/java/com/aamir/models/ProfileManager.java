package com.aamir.models;

import java.util.HashMap;
import java.util.Map;

public class ProfileManager {
    private static Map<String, profile> profiles = new HashMap<>();
    private static profile currentProfile = null;

    public static boolean createProfile(String username, String platform) {
        if (profiles.containsKey(username)) return false;
        profile newProfile = new profile(username, platform);
        profiles.put(username, newProfile);
        currentProfile = newProfile;
        return true;
    }

    public static boolean selectProfile(String username) {
        if (profiles.containsKey(username)) {
            currentProfile = profiles.get(username);
            return true;
        }
        return false;
    }

    public static profile getCurrentProfile() {
        return currentProfile;
    }

    public static Map<String, profile> getAllProfiles() {
        return profiles;
    }
}
