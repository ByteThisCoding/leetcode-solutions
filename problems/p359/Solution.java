class Logger {

    private Map<String, Integer> messageExpirations = new HashMap<>();
    
    public boolean shouldPrintMessage(int timestamp, String message) {
        if (messageExpirations.getOrDefault(message, 0) > timestamp) {
            return false;
        }

        int expiration = timestamp + 10;
        messageExpirations.put(message, expiration);
        return true;
    }
}