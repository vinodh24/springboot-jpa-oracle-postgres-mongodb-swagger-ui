package com.vinodh.service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.vinodh.mongodb.model.DbSequence;

@Service
public class SequenceGeneratorService {

	@Autowired
	private MongoOperations mongoOperations;

	public int getSequenceNumber(String sequenceName) {
		//get sequence no
		Query query = new Query(Criteria.where("id").is(sequenceName));
		//update the sequence no
		Update update = new Update().inc("seqNo", 1);
		//modify in document
		DbSequence counter = mongoOperations
				.findAndModify(query,
						update, options().returnNew(true).upsert(true),
						DbSequence.class);
		return !Objects.isNull(counter) ? counter.getSeqNo() : 1;
	}
}
