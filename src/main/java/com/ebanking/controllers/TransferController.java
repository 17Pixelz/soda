package com.ebanking.controllers;


import com.ebanking.Metier.ITransfertMetier;
import com.ebanking.dao.CompteAgentRepo;
import com.ebanking.dto.BenificiareRequest;
import com.ebanking.dto.TransfertRequest;
import com.ebanking.exception.*;
import com.ebanking.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transfert")

public class TransferController {
    @Autowired
    private ITransfertMetier transfertService;

    @Autowired
    private CompteAgentRepo compteAgentRepo;

    @GetMapping("/transactions/{id}")
    public ResponseEntity<ServiceResponse> GetTransactionsbyClientAcc(@PathVariable("id") int id){
        ServiceResponse response = new ServiceResponse();
        try{
        List<Transfert> transfertparClients= transfertService.GetAlltransfertbyclient(id);
        response.setStatus("200");
        if(transfertparClients==null){
            response.setDescription("Client don't have any transactions");
            response.setData(null);
            return new ResponseEntity(response, HttpStatus.OK);
        }else{
            response.setDescription("Client already have done some transactions");
            response.setData(transfertparClients);
            return new ResponseEntity(response, HttpStatus.OK);}
        }catch (CompteClNotFound compteClNotFound){
            response.setStatus(String.valueOf(HttpStatus.EXPECTATION_FAILED));
            response.setDescription(compteClNotFound.getMessage());
            return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping("/newtransaction/telnbr/{telnbr}/montant/{montant}/notif/{notif}/motif/{motif}/option/{option}/benificiare/{benificiare}/" +
            "commissin/{comission}/delai/{delai}")
    public ResponseEntity<ServiceResponse> NewTransactionbyClientAcc(@PathVariable("telnbr") String telnbr,
                                                                     @PathVariable("montant")Double montant,
                                                                     @PathVariable("notif") boolean notif,
                                                                     @PathVariable("option")int option,
                                                                     @PathVariable("benificiare") int idbeneficiaire,
                                                                     @PathVariable("comission")Double comission,
                                                                     @PathVariable("delai")int delai,
                                                                     @PathVariable("motif")String motif ) {

        ServiceResponse response=new ServiceResponse();
        try {
            CompteCl compteCl=transfertService.findByTelnumber(telnbr);
            transfertService.transfer_CL(compteCl.getId(),idbeneficiaire,montant,comission,option,delai,motif,notif);
            response.setStatus("200");
            response.setData(montant);
            response.setDescription("Amount :"+montant+" transferred successfully");
            return new ResponseEntity<>(response,HttpStatus.OK);

        }catch (CompteClNotFound e){
            response.setStatus(String.valueOf(HttpStatus.NOT_FOUND));
            response.setDescription(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } catch (ClientNotFound e) {
            response.setStatus(String.valueOf(HttpStatus.NOT_FOUND));
            response.setDescription(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }catch (OutOfLimitAmountTransfert e){
            response.setStatus(String.valueOf(HttpStatus.NOT_EXTENDED));
            response.setDescription(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_EXTENDED);
        }catch (OutOfLimitAmountTrnsfertAnnuel e){
            response.setStatus(String.valueOf(HttpStatus.NOT_EXTENDED));
            response.setDescription(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_EXTENDED);
        } catch (KycNotFound e) {
            response.setStatus(String.valueOf(HttpStatus.NOT_EXTENDED));
            response.setDescription(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_EXTENDED);
        }
    }

    public ResponseEntity<ServiceResponse> NewTransactionbyAgentAcc(TransfertRequest transfertRequest) {

        ServiceResponse response = new ServiceResponse();
        try {
            transfertService.transfer_CA(transfertRequest);
            response.setStatus("200");
            response.setDescription("Amount :"+transfertRequest.getMontant()+" transferred successfully");
            response.setData(transfertRequest);
            return new ResponseEntity<>(response,HttpStatus.OK);

        } catch (AgentNotFound | KycNotFound | ClientNotFound | UserNotFound e) {
            response.setStatus(String.valueOf(HttpStatus.NOT_EXTENDED));
            response.setDescription(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_EXTENDED);
        }
    }

























    @PostMapping("/reverse/{id}")
    public  ResponseEntity<ServiceResponse> ReverseTransfert(@PathVariable("id")int id){
        ServiceResponse response=new ServiceResponse();
        try {
            transfertService.ReverseTransfert(id);
            response.setStatus("200");
            response.setData("Etat changé à Restitué");
            response.setDescription("Etat changé à Restitué");
            return new ResponseEntity<>(response,HttpStatus.OK);
        } catch (TransfertDoesNotExistException e) {
            response.setStatus(String.valueOf(HttpStatus.NOT_EXTENDED));
            response.setDescription(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_EXTENDED);
        }
    }    @PostMapping("/return/{id}")
    public  ResponseEntity<ServiceResponse> ReturnTransfer(@PathVariable("id")int id){
        ServiceResponse response=new ServiceResponse();
        try {
            transfertService.ReturnTransfer(id);
            response.setStatus("200");
            response.setData("Etat changé à Retourné");
            response.setDescription("Etat changé à Retourné");
            return new ResponseEntity<>(response,HttpStatus.OK);
        } catch (TransfertDoesNotExistException e) {
            response.setStatus(String.valueOf(HttpStatus.NOT_EXTENDED));
            response.setDescription(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_EXTENDED);
        }
    }
    @PostMapping("/block/{id}")
    public  ResponseEntity<ServiceResponse> BlockTransfert(@PathVariable("id")int id){
        ServiceResponse response=new ServiceResponse();
        try {
            transfertService.BlockTransfert(id);
            response.setStatus("200");
            response.setData("Etat changé à Bloqué");
            response.setDescription("Etat changé à Bloqué");
            return new ResponseEntity<>(response,HttpStatus.OK);
        } catch (TransfertDoesNotExistException e) {
            response.setStatus(String.valueOf(HttpStatus.NOT_EXTENDED));
            response.setDescription(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_EXTENDED);
        }
    }
    @PostMapping("/unblock/{id}")
    public  ResponseEntity<ServiceResponse> UnblockTransfert(@PathVariable("id")int id){
        ServiceResponse response=new ServiceResponse();
        try {
            transfertService.UnblockTransfert(id);
            response.setStatus("200");
            response.setData("Etat changé à Débloqué");
            response.setDescription("Etat changé à Débloqué");
            return new ResponseEntity<>(response,HttpStatus.OK);
        } catch (TransfertDoesNotExistException e) {
            response.setStatus(String.valueOf(HttpStatus.NOT_EXTENDED));
            response.setDescription(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_EXTENDED);
        }
    }
    @PostMapping("/desherence/{id}")
    public  ResponseEntity<ServiceResponse> DéshérenceTransfert(@PathVariable("id")int id){
        ServiceResponse response=new ServiceResponse();
        try {
            transfertService.DéshérenceTransfert(id);
            response.setStatus("200");
            response.setData("Etat changé en déshérence");
            response.setDescription("Etat changé en déshérence");
            return new ResponseEntity<>(response,HttpStatus.OK);
        } catch (TransfertDoesNotExistException e) {
            response.setStatus(String.valueOf(HttpStatus.NOT_EXTENDED));
            response.setDescription(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_EXTENDED);
        }
    }

    @GetMapping("transfertsagent/{id}")
    public ResponseEntity<ServiceResponse> GetAlltransfertbyagent(@PathVariable("id")int id){
        ServiceResponse response=new ServiceResponse();
        try {
           List<Transfert> transfertsparagent= transfertService.GetAlltransfertbyagent(id);
           response.setStatus("200");
           response.setDescription("Voici une liste des transferts envoyé par l'agnet "+compteAgentRepo.findById(id).orElse(null).getAgent().getUser().getUsername());
           response.setData(transfertsparagent);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (CompteAgentNotFound e){
            response.setStatus(String.valueOf(HttpStatus.NOT_EXTENDED));
            response.setDescription(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_EXTENDED);
        }


    }

    @PostMapping("/benificiare")
    public ResponseEntity<ServiceResponse> saveBenificiare(BenificiareRequest request){
        ServiceResponse response=new ServiceResponse();
        Beneficiaire beneficiaire=transfertService.saveBenificiare(request.getNom(), request.getPrenom(), request.getGsm());
        response.setStatus("200");
        response.setDescription("Benificiary added");
        response.setData(beneficiaire);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }




}
