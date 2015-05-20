package com.spencer.project1;

import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.Rest;
import org.json.JSONObject;
import org.springframework.http.converter.StringHttpMessageConverter;

/**
 * Created on 5/19/15.
 * @author Adrian Pena
 */
@Rest(converters = StringHttpMessageConverter.class)
public interface PersonRepository {

    /**
     * Gets a list of people.
     * @return a JSON-formatted string with a list of people.
     */
    @Get("http://person.rastadrian.com/person")
    String getPeople();

    /**
     * Gets a person. The array only has 3 items so basically from 0 to 2.
     * @param index the index of the person in the array.
     * @return a JSON-formated string with a person.
     */
    @Get("http://person.rastadrian.com/person/{index}")
    String getPerson(int index);

    @Post("http://person.rastandrian.com/person/{person}")
    void addPerson(String person);
}