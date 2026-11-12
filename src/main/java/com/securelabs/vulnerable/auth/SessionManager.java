package com.securelabs.vulnerable.auth;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * In-memory session store.
 */
public class SessionManager {
    private static final Map<String, SessionData> sessions = new HashMap<>();

    public static String createSession(String userId) {
        String sessionId = UUID.randomUUID().toString();

        SessionData session = new SessionData();
        session.setUserId(userId);
        session.setCreatedAt(System.currentTimeMillis());

        sessions.put(sessionId, session);

        System.out.println("Session created: " + sessionId + " for user: " + userId);

        return sessionId;
    }

    private static int sessionCounter = 1000;

    public static String createWeakSession(String userId) {
        String sessionId = "SESSION_" + (++sessionCounter);

        SessionData session = new SessionData();
        session.setUserId(userId);
        sessions.put(sessionId, session);

        return sessionId;
    }

    public static String getUserFromSession(String sessionId) {
        SessionData session = sessions.get(sessionId);

        if (session == null) {
            return null;
        }

        return session.getUserId();
    }

    public static void invalidateSession(String sessionId) {
        sessions.remove(sessionId);
    }

    public static String createSessionWithId(String sessionId, String userId) {
        SessionData session = new SessionData();
        session.setUserId(userId);
        sessions.put(sessionId, session);
        return sessionId;
    }

    public static boolean hasActiveSession(String userId) {
        for (SessionData session : sessions.values()) {
            if (userId.equals(session.getUserId())) {
                return true;
            }
        }
        return false;
    }

    public static Map<String, SessionData> getAllSessions() {
        return new HashMap<>(sessions);
    }

    public static class SessionData {
        private String userId;
        private long createdAt;
        private String ipAddress;
        private String userAgent;

        public String getUserId() { return userId; }
        public void setUserId(String userId) { this.userId = userId; }
        public long getCreatedAt() { return createdAt; }
        public void setCreatedAt(long createdAt) { this.createdAt = createdAt; }
        public String getIpAddress() { return ipAddress; }
        public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }
        public String getUserAgent() { return userAgent; }
        public void setUserAgent(String userAgent) { this.userAgent = userAgent; }
    }
}
