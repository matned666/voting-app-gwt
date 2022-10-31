package com.herokuapp.mrndesign.matned.client.model.http;

import com.google.gwt.http.client.*;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.herokuapp.mrndesign.matned.client.model.Model;
import com.herokuapp.mrndesign.matned.client.model.dto.Candidate;
import com.herokuapp.mrndesign.matned.client.model.dto.Voter;
import com.herokuapp.mrndesign.matned.client.model.utils.VoteObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class HttpRequesterImpl implements HttpRequester {
    private static final Logger logger = java.util.logging.Logger.getLogger("HttpRequesterImpl");

    private final Model model;

    public HttpRequesterImpl(Model model) {
        this.model = model;
    }

    @Override
    public void saveVoter(Voter voter) {
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST, "http://localhost:8080/voters");
        try {
            requestBuilder.setHeader("Content-Type", "application/json; charset=utf8");
            requestBuilder.setHeader("Accept", "application/json");
            requestBuilder.sendRequest(voter.toString(), new RequestCallback() {
                @Override
                public void onResponseReceived(Request request, Response response) {
                    JSONValue value = JSONParser.parseStrict(response.getText());
                    JSONObject v = value.isObject();
                    Long id = (long) v.get("id").isNumber().doubleValue();
                    String name = v.get("name").isString().stringValue();
                    String surname = v.get("surname").isString().stringValue();
                    model.onSaveVoterResultReceive(new Voter(id, name, surname));
                }

                @Override
                public void onError(Request request, Throwable exception) {
                }
            });
        } catch (RequestException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void vote(VoteObserver voteObserver) {
        String url = "http://localhost:8080/giveVote/" + voteObserver.getSelectedVoter().getId() + "/" + voteObserver.getSelectedCandidate().getId();
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST, url);
        try {
            requestBuilder.sendRequest(null, new RequestCallback() {
                @Override
                public void onResponseReceived(Request request, Response response) {

                }

                @Override
                public void onError(Request request, Throwable exception) {

                }
            });
        } catch (RequestException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void requestVoters() {
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, "http://localhost:8080/voters");
        try {
            requestBuilder.sendRequest(null, new RequestCallback() {
                @Override
                public void onResponseReceived(Request request, Response response) {
                    List<Voter> voters = new ArrayList<>();
                    JSONValue value = JSONParser.parseStrict(response.getText());
                    JSONArray obj = value.isArray();
                    for (int i = 0; i < obj.size(); i++) {
                        JSONObject v = obj.get(i).isObject();
                        Long id = (long) v.get("id").isNumber().doubleValue();
                        String name = v.get("name").isString().stringValue();
                        String surname = v.get("surname").isString().stringValue();
                        Voter e = new Voter(id, name, surname);
                        logger.info("RESPONSE:" + id + " " + name + " " + surname);
                        voters.add(e);
                    }
                    model.onGetVotersResultReceive(voters);
                }

                @Override
                public void onError(Request request, Throwable exception) {
                }
            });
        } catch (RequestException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void requestCandidates() {
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, "http://localhost:8080/candidates");
        try {
            requestBuilder.sendRequest(null, new RequestCallback() {
                @Override
                public void onResponseReceived(Request request, Response response) {
                    List<Candidate> candidates = new ArrayList<>();
                    JSONValue value = JSONParser.parseStrict(response.getText());
                    JSONArray obj = value.isArray();
                    for (int i = 0; i < obj.size(); i++) {
                        List<Long> votes = new ArrayList<>();
                        JSONObject v = obj.get(i).isObject();
                        Long id = (long) v.get("id").isNumber().doubleValue();
                        Long voterId = (long) v.get("voterId").isNumber().doubleValue();
                        JSONArray vts = v.get("listOfVotesIds").isArray();
                        for (int j = 0; j < vts.size(); j++) {
                            votes.add((long) vts.get(j).isNumber().doubleValue());
                        }
                        candidates.add(new Candidate(id, voterId, votes));
                    }
                    model.onGetCandidatesResultReceive(candidates);
                }

                @Override
                public void onError(Request request, Throwable exception) {
                }
            });
        } catch (RequestException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveCandidate(Candidate candidate) {
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST, "http://localhost:8080/candidates/" + candidate.getVoterId());
        requestBuilder.setHeader("Content-Type", "application/json; charset=utf8");
        requestBuilder.setHeader("Accept", "application/json");
        try {
            requestBuilder.sendRequest(null, new RequestCallback() {
                @Override
                public void onResponseReceived(Request request, Response response) {
                    JSONValue value = JSONParser.parseStrict(response.getText());
                    JSONObject v = value.isObject();
                    List<Long> votes = new ArrayList<>();
                    Long id = (long) v.get("id").isNumber().doubleValue();
                    Long voterId = (long) v.get("voterId").isNumber().doubleValue();
                    JSONArray vts = v.get("listOfVotesIds").isArray();
                    for (int j = 0; j < vts.size(); j++) {
                        votes.add((long) vts.get(j).isNumber().doubleValue());
                    }
                    model.onCandidateSaveResultReceive(new Candidate(id, voterId, votes));
                }

                @Override
                public void onError(Request request, Throwable exception) {
                }
            });
        } catch (RequestException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void removeVoter(Voter voter) {
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.DELETE, "http://localhost:8080/voter/" + voter.getId());
        try {
            requestBuilder.sendRequest(null, new RequestCallback() {
                @Override
                public void onResponseReceived(Request request, Response response) {

                }

                @Override
                public void onError(Request request, Throwable exception) {

                }
            });
        } catch (RequestException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeCandidate(Candidate candidate) {
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.DELETE, "http://localhost:8080/candidate/" + candidate.getId());
        try {
            requestBuilder.sendRequest(null, new RequestCallback() {
                @Override
                public void onResponseReceived(Request request, Response response) {

                }

                @Override
                public void onError(Request request, Throwable exception) {

                }
            });
        } catch (RequestException e) {
            throw new RuntimeException(e);
        }
    }

}
