# Tickets

Is a REST interface to generate a ticket with n lines. Additionally it
has the ability to check the status of lines on a ticket. The lines are sorted into
outcomes before being returned. It should be possible for a ticket to be amended with n
additional lines. Once the status of a ticket has been checked it should not be possible to
amend.

## Running

In general, you can use the following command to run the app:

`./gradlew bootRun`

That will run the SpringBoot application, but you will need other resources available like a
database, external services, so it will run properly.

