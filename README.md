# reservation

An application to make reservations in a restaurant.

## Workflow:

The waiter opens the app, chooses a customer from a list, the app navigates him to a table choosing
screen. The waiter chooses the table and the app highlights the chosen table. Every 15 minutes all
reservations have to be removed even if the application is closed.
## API description:
Each customer has First Name, Last Name and Id.
The tables are represented as one-dimensional array of booleans where “true” means that the table is
available and “false” that it is not.
## Requirement:

1. Tables have to be represented as cells on grid
2. Available and unavailable tables have to be easily recognized
3. The app has to also work offline
