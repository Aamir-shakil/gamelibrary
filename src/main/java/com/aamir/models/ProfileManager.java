package com.aamir.models;

import java.io.*;
import java.util.*;

/*
 * Manages user profiles including creation, selection, saving, and loading.
 * Profiles are serialized and stored on disk for persistence between sessions.
 */

public class ProfileManager {

    private static final String SAVE_FOLDER = "profiles/";
    private static Map<String, profile> profiles = new HashMap<>();
    private static profile currentUser = null;

    /*
     * Creates a new profile with the given username and platform.
     * Automatically selects and saves the new profile.
     */
    public static boolean createProfile(String username, String platform) {
        if (profiles.containsKey(username)) return false;

        profile newProfile = new profile(username, platform);
        profiles.put(username, newProfile);
        currentUser = newProfile;
        saveProfile(newProfile);
        return true;
    }

    //Selects an existing profile by username.
    public static boolean selectProfile(String username) {
        if (!profiles.containsKey(username)) return false;
        currentUser = profiles.get(username);
        return true;
    }

    public static profile getCurrentUser() {
        return currentUser;
    }

    public static profile getCurrentProfile() {
        return currentUser;
    }

    //Saves a profile to disk using Java serialization.
    public static void saveProfile(profile p) {
        try {
            File dir = new File(SAVE_FOLDER);
            if (!dir.exists()) dir.mkdirs();

            ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(SAVE_FOLDER + p.getUsername() + ".dat"));
            out.writeObject(p);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Loads all serialized profiles from the profiles directory into memory.
     * Automatically adds each loaded profile to the profiles map.
     */
    public static void loadAllProfiles() {
        File dir = new File(SAVE_FOLDER);
        if (!dir.exists()) dir.mkdirs();

        File[] files = dir.listFiles((d, name) -> name.endsWith(".dat"));
        if (files == null) return;

        for (File file : files) {
            try {
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
                profile p = (profile) in.readObject();
                profiles.put(p.getUsername(), p);
                in.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    // Retrieves the set of all profile usernames that are currently loaded.
    public static Set<String> getAllProfileNames() {
        return profiles.keySet();
    }
}
