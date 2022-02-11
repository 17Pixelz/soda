package com.ebanking.Metier.Imp;

import com.ebanking.dto.TransfertRequest;
import com.ebanking.exception.*;
import com.ebanking.Metier.ITransfertMetier;
import com.ebanking.constant.Constants;
import com.ebanking.dao.*;
import com.ebanking.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class TransfertService implements ITransfertMetier {

    @Autowired
    private TransfertRepo transfertRepository;

    @Autowired
    private AgentRepository agentRepository;
    @Autowired
    private KycRepository kycRepository;

    @Autowired
    private CompteAgentRepo compteAgentRepo;

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private CompteClRepository compteClRepository;


    @Autowired
    private ClientProspectsRepo clientProspectsRepo;

    @Autowired
    private BeneficiaireRepository beneficiaireRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public CompteCl findByTelnumber(String telnumber) throws ClientNotFound, KycNotFound {
        KYC kyc=kycRepository.findByGSM(telnumber);
        if(kyc==null){
            Client client=kyc.getClient();
            if(client==null){
                throw new ClientNotFound();
            }else {
                CompteCl compteCl= client.getCompte();
                if(compteCl==null){
                    throw new CompteClNotFound();
                }else{
                    return compteCl;
                }
            }
        }else {
            throw new KycNotFound();
        }

    }
    @Override
    public Client findbyIdentité(String cin) throws ClientNotFound, KycNotFound {
        KYC kyc=kycRepository.findByNumPiece(cin);
        if(kyc==null) {
            Client client = kyc.getClient();
            if (client == null) {
                throw new ClientNotFound();
            } else {
                return client;
            }
        }else{
            throw new KycNotFound();
        }
    }





    @Override
    public Beneficiaire saveBenificiare(String nom, String prenom, String phone) {
        Beneficiaire beneficiaire=new Beneficiaire(nom,prenom,phone);
        beneficiaireRepository.save(beneficiaire);
        return beneficiaire;
    }
    @Override
    public void ReverseTransfert(int transfid) throws TransfertDoesNotExistException {
        Transfert transfert=transfertRepository.findById(transfid).orElse(null);
        if(transfert==null){
            throw new TransfertDoesNotExistException(transfid);
        }else{
            transfert.setEtat(Etat.Extourné);
            transfertRepository.save(transfert);
        }
    }

    @Override
    public void ReturnTransfer(int transfid) throws TransfertDoesNotExistException {
        Transfert transfert=transfertRepository.findById(transfid).orElse(null);
        if(transfert==null){
            throw new TransfertDoesNotExistException(transfid);
        }else{
            transfert.setEtat(Etat.Restitué);
            transfertRepository.save(transfert);
        }
    }

    @Override
    public void BlockTransfert(int transfid) throws TransfertDoesNotExistException {
        Transfert transfert=transfertRepository.findById(transfid).orElse(null);
        if(transfert==null){
            throw new TransfertDoesNotExistException(transfid);
        }else{
            transfert.setEtat(Etat.Bloqué);
            transfertRepository.save(transfert);
        }
    }

    @Override
    public void UnblockTransfert(int transfid) throws TransfertDoesNotExistException {
        Transfert transfert=transfertRepository.findById(transfid).orElse(null);
        if(transfert==null){
            throw new TransfertDoesNotExistException(transfid);
        }else{
            transfert.setEtat(Etat.debloqué_a_servir);
            transfertRepository.save(transfert);
        }
    }

    @Override
    public void DéshérenceTransfert(int transfid) throws TransfertDoesNotExistException {
        Transfert transfert=transfertRepository.findById(transfid).orElse(null);
        if(transfert==null){
            throw new TransfertDoesNotExistException(transfid);
        }else{
            transfert.setEtat(Etat.en_déshérence);
            transfertRepository.save(transfert);
        }
    }


    ////////////*////////////////////*//////////////////////////////////////////*////////////////////////////////////////////////
    ////////////*////////////////////*//////////////////////////////////////////*////////////////////////////////////////////////
    ////////////*////////////////////*//////////////////////////////////////////*////////////////////////////////////////////////
    @Override
    public void transfer_CL(int compteClid, int beneficiaireid, Double montant, Double comission,
                            int option, int delaiparheurs,String motif,boolean notification)
                    throws OutOfLimitAmountTransfert, OutOfLimitAmountTrnsfertAnnuel {
        LocalDateTime date=LocalDateTime.now();
        String typedefrais="";
        Double montantrecu=0.0;
        Double montantpaye=0.0;
        CompteCl compteCl=compteClRepository.findById(compteClid).orElse(null);
        Beneficiaire beneficiaire=beneficiaireRepository.findById(beneficiaireid).orElse(null);
        if(compteCl==null) {
            throw new CompteClNotFound();
        }else if(beneficiaire==null) {
            throw new BenificiaireNotFound();
        }
        if(montant>Constants.PLAFONT_TRANSFERT_WALLET_CL){
            throw new OutOfLimitAmountTransfert();
        }
        else if(montant+compteCl.getSomme_transferts_Année()>Constants.PLAFONT_TRANSFERT_WALLET_CL_ANNUEL){
            throw new OutOfLimitAmountTrnsfertAnnuel(compteCl);
        }
        else{
            if(option==0){
                typedefrais="Transfert payé par le donneur d'ordre.";
                montantrecu=montant;
                montantpaye=montant+comission;
            }else if(option==1){
                typedefrais="Transfert payé par le bénificiaire .";
                montantrecu=montant-comission;
                montantpaye=montant;
            }else if(option ==2){
                typedefrais="Le prix de transfert partagé entre le donneur d'ordre et le bénificiare .";
                montantrecu=montant-comission*50/100;
                montantpaye=montant+comission+50/100;
            }
            if(notification) {

            }else {


            }
            compteCl.setSoldeCompte(compteCl.getSoldeCompte()-montantpaye);
            compteCl.setSomme_transferts_Année(compteCl.getSomme_transferts_Année()+montant);
            TransfertparClient transfertparClient =new TransfertparClient(delaiparheurs,compteCl,beneficiaire,"TransfertparClient",typedefrais,montant,comission);
            transfertRepository.save(transfertparClient);
        }


    }
    @Override
    public List<Transfert> GetAlltransfertbyclient(int id) {
        CompteCl compteCl=compteClRepository.findById(id).orElse(null);
        if(compteCl==null){
            throw new CompteClNotFound();

        }else{
            return transfertRepository.findTransfertByTypeTransfertAndCompteCl("TransfertparAgent",compteCl);
        }

    }
    @Override
    public List<Transfert> GetAlltransfertbyclients() {
        return transfertRepository.findTransfertByTypeTransfert("TransfertparClient");
    }
    @Override
    public Transfert Getransfertparclient(int id) throws TransactionClNotFound {
        Transfert transfertparClient=transfertRepository.findById(id).orElse(null);
        if(transfertparClient==null) throw new TransactionClNotFound();
        else {
            return transfertparClient;

        }
    }
    @Override
    public List<Beneficiaire> GetBenificairesparClient(CompteCl compteCl) {
        List<Beneficiaire> beneficiaires=compteCl.getBeneficiaires();
        return beneficiaires;
    }

    ////////////*////////////////////*//////////////////////////////////////////*////////////////////////////////////////////////
    ////////////*////////////////////*//////////////////////////////////////////*////////////////////////////////////////////////
    ////////////*////////////////////*//////////////////////////////////////////*////////////////////////////////////////////////


    @Override
    public void transfer_CA(TransfertRequest request) throws ClientNotFound, CompteAgentNotFound, AgentNotFound, KycNotFound, UserNotFound {
        Date date=new Date();
        String typedeFrais="";
        Double montantrecu=0.0;
        Double montantpaye=0.0;
        User user=userRepository.findById(request.getIduser()).orElse(null);
        if(user==null){
            throw new UserNotFound();
        }else{
            Agent agent=agentRepository.findByUser(user).orElse(null);
            if(agent==null){
                throw new AgentNotFound();
            }else {
                CompteAgent compteAgent=agent.getCompteAgent();
                Client client=findbyIdentité(request.getIdentifiant());
                Beneficiaire beneficiaire=beneficiaireRepository.findById(request.getIdbeneficiaire()).orElse(null);
                if(client==null){
                    throw new ClientNotFound();

                }else if(compteAgent==null){
                    throw new CompteAgentNotFound();
                }
                if(request.getTypedefrais()==0){
                    typedeFrais="Transfert payé par le donneur d'ordre.";
                    montantrecu=request.getMontant();
                    montantpaye=request.getMontant()+Constants.MONTANT_FRAIS;
                }else if(request.getTypedefrais()==1){
                    typedeFrais="Transfert payé par le bénificiaire .";
                    montantrecu=request.getMontant()-Constants.MONTANT_FRAIS;
                    montantpaye=request.getMontant();
                }else if(request.getTypedefrais() ==2){
                    typedeFrais="Le prix de transfert partagé entre le donneur d'ordre et le bénificiare .";
                    montantrecu=request.getMontant()-Constants.MONTANT_FRAIS;
                    montantpaye=request.getMontant()+Constants.MONTANT_FRAIS;
                }
                compteAgent.setSoldeCompte(compteAgent.getSoldeCompte()-montantpaye);
                TransfertparAgent transfertparAgent =new TransfertparAgent(request.getDelai(),client,compteAgent,beneficiaire,"TransfertparAgent",typedeFrais,request.getMontant(),Constants.MONTANT_FRAIS);
                transfertRepository.save(transfertparAgent);
            }
        }



    }



    @Override
    public List<Transfert> GetAlltransfertbyagents() {
        return transfertRepository.findTransfertByTypeTransfert("TransfertparAgent");
    }



    @Override
    public List<Transfert>GetAlltransfertbyagent(int id) {
        CompteAgent compteAgent=compteAgentRepo.findById(id).orElse(null);
        if(compteAgent==null){
            throw new CompteAgentNotFound();

        }else{
            return transfertRepository.findTransfertByTypeTransfertAndCompteAgent("TransfertparAgent",compteAgent);
        }
    }



    @Override
    public Transfert Gettransfertparagent(int id) throws TransactionAgentNotFound {
      Transfert transfertparAgent=transfertRepository.findById(id).orElse(null);
      if(transfertparAgent==null) throw new TransactionAgentNotFound();
      else {
          return transfertparAgent;
      }
    }




}



