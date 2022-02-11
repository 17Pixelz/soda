//package com.ebanking.Components;
//
//
//import com.ebanking.dao.BeneficiaireRepository;
//import com.ebanking.models.Etat;
//import com.ebanking.models.TransfertparClient;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//import java.util.List;
//
//@Component
//public class BénificiareComponent {
//
//    private static final Logger log = LoggerFactory.getLogger(BénificiareComponent.class);
//    private static long count = 0;
//
//    @Autowired
//    private TransfertparClientRepository transfertparClientRepository;
//    @Autowired
//    private BeneficiaireRepository beneficiaireRepository;
//
//
//
//    @Scheduled(fixedRate = 1000)
//    public void serviebénificiares() {
//        List<TransfertparClient> transfertparClients=transfertparClientRepository.findAll();
//        for(TransfertparClient transfertparClient:transfertparClients){
//            if(transfertparClient.getDateArrivé()==new Date()){
//                transfertparClient.setEtat(Etat.Servie);
//                log.info("yes");
//            }
//        }
//
//
//    }
//}
