package com.mediscreen.history.repository;

import com.mediscreen.history.model.PatientHistory;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;
import static com.mongodb.client.model.Filters.eq;

import java.util.List;

import config.DatabaseConfig;

@Repository
public class MongoDbPatientHistoryRepository implements PatientHistoryRepository {

    DatabaseConfig data = new DatabaseConfig();
    MongoClient client = data.mongoClient();
	MongoDatabase database = data.getDatabase(client);
    MongoCollection<PatientHistory> historyCollection = database.getCollection("patientHistory", PatientHistory.class);
		
		
    @Override
    public PatientHistory save(PatientHistory patientHistory) {
        historyCollection.insertOne(patientHistory);
        return patientHistory;
    }

    @Override
    public PatientHistory findOne(int patientId) {
        return historyCollection.find(eq("patientId", patientId)).first();
    }

    public FindIterable<PatientHistory> find(int patientId) {
        return historyCollection.find(eq("patientId", patientId));
    }

    @Override
    public PatientHistory update(PatientHistory patientHistory) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
