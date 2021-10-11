# Know your MPs!

🗳️ Browse members of the Parliament of Finland, rate them, and attach important notes for the future!

## Features

- Browse information about members of the Finnish parliament
- Browse parties, view their members, and inspect the members' personal details, such as name, age, and profile picture
- Attach notes to each Member of Parliament (MP)

## Work-in-progress

- Give a positive or negative rating to MPs
- Improve UI and UX
- More party details

## Technical documentation
- Fetch MP information from an API using [Retrofit](https://github.com/square/retrofit) and [Moshi](https://github.com/square/moshi)
- Employ [WorkManager](https://developer.android.com/reference/androidx/work/WorkManager) to re-fetch the data at 1 hour intervals
- Store MP information in [RoomDatabase](https://developer.android.com/reference/android/arch/persistence/room/RoomDatabase) from where it can easily be accessed
- Display and cache MP profile pictures using the [Picasso](https://github.com/square/picasso) library
- Use [Hilt](https://developer.android.com/jetpack/androidx/releases/hilt) for dependency injection
- Implement [Android Jetpack](https://developer.android.com/jetpack) components and libraries to keep up with the latest standards and follow best practices
