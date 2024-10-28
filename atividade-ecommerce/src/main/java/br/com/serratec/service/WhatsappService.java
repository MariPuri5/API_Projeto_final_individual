//package br.com.serratec.service;
//
//import com.twilio.Twilio;
//import com.twilio.rest.api.v2010.account.Message;
//import com.twilio.type.PhoneNumber;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//@Service
//public class WhatsappService {
//
//    @Value("${twilio.account-sid}")
//    private String accountSid;
//
//    @Value("${twilio.auth-token}")
//    private String authToken;
//
//    @Value("${twilio.whatsapp-number}")
//    private String fromNumber;
//
//    public String enviarMensagem(String numero, String mensagem) {
//        try {
//            
//            Twilio.init(accountSid, authToken);
//
//           
//            String to = "whatsapp:" + numero;
//
//         
//            Message message = Message.creator(
//                    new PhoneNumber(to),
//                    new PhoneNumber("SAINDO" + fromNumber),
//                    mensagem).create();
//
//            return "Mensagem enviada com sucesso para: " + numero;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "Erro ao enviar mensagem: " + e.getMessage();
//        }
//    }
//}
