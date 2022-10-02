#LearnSpringMVC (Branch #9._upgrade_CRUD_(+JdbcTemplate))

        Rewrite meth index() in DAO (using JDBC)<br/>
        Rewrite meth add(Person p) in DAO (using PreparedStatement)<br/>
        Rewrite meth show(int id) in DAO (using PreparedStatement)<br/>
        Rewrite meth update(int id, Person updatedPerson) in DAO (using PreparedStatement)<br/>
        Rewrite meth delete(int id) in DAO (using PreparedStatement)<br/>

NEW:<br/>
        Added Spring-JDBC dependency<br/>
        Added beans DataSource & JdbcTemplate to SpringConfig<br/>
        Added jdbcTemplate to PeopleDAO<br/>
        Rewrite index() using jdbcTemplate<br/>
        Rewrite show() using jdbcTemplate<br/>
        Rewrite update() using jdbcTemplate<br/>
        Rewrite add() using jdbcTemplate<br/>
        Rewrite delete() using jdbcTemplate<br/>
