package com.acuevas.sudokus.controller;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.acuevas.sudokus.model.Ranking;
import com.acuevas.sudokus.model.records.Records;
import com.acuevas.sudokus.model.records.Records.Record;
import com.acuevas.sudokus.model.users.Users;
import com.acuevas.sudokus.model.users.Users.User;

public abstract class Functions {

	public static Double getMeanTime(User user, Records records) {
		Double mean = records.getRecords().stream().filter(record -> record.getUsername().equals(user.getUsername()))
				.mapToDouble(Record::getTime).average().orElse(mean = Double.NaN);
		return mean.isNaN() ? null : mean;
	}

	public static List<Ranking> getSortedRankings(Users users) {
		List<Ranking> rankings = users.getUsers().stream().map(Ranking::new).collect(Collectors.toList());
		Collections.sort(rankings);
		return rankings;
	}

}
