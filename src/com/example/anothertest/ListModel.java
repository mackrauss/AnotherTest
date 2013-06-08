package com.example.anothertest;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by armin on 13-06-05.
 */
@DatabaseTable(tableName = "listmodel")
public class ListModel {
	@DatabaseField(generatedId = true)
	private int _id;
    @DatabaseField(index = true)
    private String userInput;

    /**
     * Default Constructor needed by ormlite
     */
    public ListModel() {
    }

    /**
     * Constructor that instantiates the private member variable(s)
     * @param userInput
     */
    public ListModel(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Copy constructor
     * @param existingListModel - List model instance that is copied to new instance
     */
    public ListModel(ListModel existingListModel) {
        this.userInput = existingListModel.userInput;
    }

    /**
     * getter function for userInput (auto generated)
     * @return userInput as String
     */
    public String getUserInput() {
        return userInput;
    }

    /**
     * setter function for userInput (auto generated)
     */
    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }
}
