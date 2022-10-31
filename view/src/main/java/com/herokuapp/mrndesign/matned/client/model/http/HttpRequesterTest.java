//package com.herokuapp.mrndesign.matned.client.model.http;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.herokuapp.mrndesign.matned.client.model.dto.Candidate;
//import com.herokuapp.mrndesign.matned.client.model.dto.Voter;
//import com.herokuapp.mrndesign.matned.client.model.utils.VoteObserver;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpDelete;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.HttpClientBuilder;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.util.EntityUtils;
//
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
///**
// * It is not working with GWT
// */
//public class HttpRequesterTest implements HttpRequester {
//
//    private final ObjectMapper objectMapper;
//
//    public HttpRequesterTest() {
//        objectMapper = new ObjectMapper();
//    }
//
//    @Override
//    public boolean hasVoted(Long id) throws IOException {
//        HttpResponse response = getStringHttpResponse("http://localhost:8080/hasVoted/"+id);
//        String json =  EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
//        Boolean result = objectMapper.readValue(json, Boolean.class);
//        return result != null ? result : false ;
//    }
//
//    @Override
//    public Voter saveVoter(Voter voter) throws IOException {
//        HttpClient client = HttpClientBuilder.create().build();
//        HttpPost post = new HttpPost("http://localhost:8080/voters/");
//        StringEntity params = new StringEntity(objectMapper.writeValueAsString(voter));
//        post.addHeader("content-type", "application/json");
//        post.addHeader("accept", "application/json");
//        post.setEntity(params);
//        HttpResponse response = client.execute(post);
//
//        String json =  EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
//        System.out.println(json);
//        return objectMapper.readValue(json, Voter.class);
//    }
//
//    @Override
//    public void vote(VoteObserver voteObserver) throws IOException {
//        HttpClient client = HttpClientBuilder.create().build();
//        HttpPost post = new HttpPost("http://localhost:8080/giveVote/"+voteObserver.getSelectedVoter().getId()+"/"+voteObserver.getSelectedCandidate().getId());
//        post.addHeader("content-type", "application/json");
//        post.addHeader("accept", "application/json");
//        client.execute(post);
//    }
//
//    @Override
//    public List<Voter> getVoters() throws IOException, InterruptedException {
//        HttpResponse response = getStringHttpResponse("http://localhost:8080/voters");
//        String json =  EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
//        Voter[] voters = objectMapper.readValue(json, Voter[].class);
//        List<Voter> votersList = new ArrayList<>();
//        Collections.addAll(votersList, voters);
//        return votersList;
//    }
//
//    @Override
//    public List<Candidate> getCandidates() throws IOException, InterruptedException {
//        HttpResponse response = getStringHttpResponse("http://localhost:8080/candidates");
//        ObjectMapper objectMapper = new ObjectMapper();
//        String json =  EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
//        Candidate[] candidates = objectMapper.readValue(json, Candidate[].class);
//        List<Candidate> candidateList = new ArrayList<>();
//        Collections.addAll(candidateList, candidates);
//        return candidateList;
//    }
//
//    @Override
//    public Candidate saveCandidate(Candidate candidate) throws IOException {
//        HttpClient client = HttpClientBuilder.create().build();
//        HttpPost post = new HttpPost("http://localhost:8080/candidates/"+candidate.getVoterId());
//        StringEntity params = new StringEntity(objectMapper.writeValueAsString(candidate));
//        post.addHeader("content-type", "application/json");
//        post.addHeader("accept", "application/json");
//        post.setEntity(params);
//        HttpResponse response = client.execute(post);
//        String json =  EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
//        return objectMapper.readValue(json, Candidate.class);
//    }
//
//    @Override
//    public void removeVoter(Voter voter) throws IOException {
//        HttpClient client = HttpClientBuilder.create().build();
//        HttpDelete delete = new HttpDelete("http://localhost:8080/voters/"+voter.getId());
//        client.execute(delete);
//    }
//
//    @Override
//    public void removeCandidate(Candidate candidate) throws IOException {
//        HttpClient client = HttpClientBuilder.create().build();
//        HttpDelete delete = new HttpDelete("http://localhost:8080/candidates/"+candidate.getId());
//        client.execute(delete);
//    }
//
//    private static HttpResponse getStringHttpResponse(String str) throws IOException {
//        HttpClient client = HttpClients.createDefault();
//        HttpGet request = new HttpGet(str);
//        return client.execute(request);
//    }
//
//}
