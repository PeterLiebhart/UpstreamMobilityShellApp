package at.upstream_mobility.itacademy.bored.data;

public record FetchedActivity(
        String activity,
        double availability,
        String type,
        int participants,
        double price,
        String accessibility,
        String duration,
        boolean kidFriendly,
        String link,
        String key
) {}
