#LearnSpringMVC (Branch #5._upgrade_CRUD)

OLD:<br/>
        Simple CRUD-app without DB (using ArrayList); <br/>
        Added a functional to create new person;
        Added a method-filter in DispatcherServletInitializer; <br/>
        Added a button "Add new person" in index.html; <br/>
        Added a functional to edit person:
        Added a functional to delete person:
<hr/>
NEW:<br/>
        Added a Hibernate-validator dependency in pom
        Added a fields (email, age) to Person model<br/>
        Added an age to index.html<br/>
        Added an age and email to show.html<br/>
        Update meth "update" in PeopleDAO;
        Added fields in new.html<br/>
        Added fields in edit.html<br/>
        Added a valid-annotations to fields<br/>
        Added a validation to controller<br/>
        Added an error blocks to new.html<br/>
        Added an error blocks to edit.html

        