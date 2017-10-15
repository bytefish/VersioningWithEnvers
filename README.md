# Versioning and Auditing with Hibernate Envers #

## Project ##

This project is an example project for Versioning and Auditing Database Tables with Hibernate Envers:

* https://bytefish.de/blog/hibernate_envers_versioning_and_auditing/

### Example ###

We start by inserting a new Customer into the database. We are using the Username ``Philipp``, which is passed in the ``X-Username`` header:

```
> curl -H "X-Username: Philipp" -H "Content-Type: application/json" -X POST -d "{\"firstName\" : \"Philipp\", \"lastName\" : \"Wagner\"}"  http://localhost:8080/customers

{"id":26,"firstName":"Philipp","lastName":"Wagner"}
```

Now we can query the ``history`` resource for the customer. At the moment there is only one revision of type ``add``:

```
> curl -H "X-Username: Philipp" -X GET http://localhost:8080/history/customer/26

[{"customer":{"id":26,"firstName":"Philipp","lastName":"Wagner"},"revision":37,"type":"add"}]
```

Now let's change the last name from ``Wagner`` to ``Mustermann``:

```
> curl -H "X-Username: Philipp" -H "Content-Type: application/json" -X POST -d "{\"id\" : 26, \"firstName\" : \"Philipp\", \"lastName\" : \"Mustermann\"}"  http://localhost:8080/customers

{"id":26,"firstName":"Philipp","lastName":"Mustermann"}
```

And in the history we will now see a new revision ``38`` with the type ``mod`` in the history, which shows the modifications:

```
> curl -H "X-Username: Philipp" -X GET http://localhost:8080/history/customer/26

[{"customer":{"id":26,"firstName":"Philipp","lastName":"Wagner"},"revision":37,"type":"add"},{"customer":{"id":26,"firstName":"Philipp","lastName":"Mustermann"},"revision":38,"type":"mod"}]
```

And if we finally delete the Customer:

```
> curl -H "X-Username: Philipp" -X DELETE http://localhost:8080/customers/26
```

Then we will see a new revision ``42`` with the type ``del`` in the results:

```
> curl -H "X-Username: Philipp" -X GET http://localhost:8080/history/customer/26

[{"customer":{"id":26,"firstName":"Philipp","lastName":"Wagner"},"revision":37,"type":"add"},{"customer":{"id":26,"firstName":"Philipp","lastName":"Mustermann"},"revision":38,"type":"mod"},{"customer":{"id":26,"firstName":"Philipp","lastName":"Mustermann"},"revision":42,"type":"del"}]
