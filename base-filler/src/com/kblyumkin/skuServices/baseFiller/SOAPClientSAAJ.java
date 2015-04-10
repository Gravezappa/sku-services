package com.kblyumkin.skuServices.baseFiller;

import javax.xml.soap.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import java.util.Random;

public class SOAPClientSAAJ {

    /**
     * Starting point for the SAAJ - SOAP Client Testing
     */
    public static void main(String args[]) {
        String[] descriptions = {"Some description","Another description","Go-go description", "No description"};
        Random random = new Random();
        try {
            for (int i = 17; i < 2000001; i++) {
                // Create SOAP Connection
                SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
                SOAPConnection soapConnection = soapConnectionFactory.createConnection();

                // Send SOAP Message to SOAP Server
                String url = "http://127.0.1.1:8080/sku-services/userService";
                SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(i, descriptions[random.nextInt(4)]), url);

                // Process the SOAP Response
                printSOAPResponse(soapResponse);

                soapConnection.close();
            }
        } catch(Exception e) {
            System.err.println("Error occurred while sending SOAP Request to Server");
            e.printStackTrace();
        }
    }

    private static SOAPMessage createSOAPRequest(int number, String description) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();

        String serverURI = "http://services.skuServices.kblyumkin.com/";

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("ser", serverURI);

        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElem = soapBody.addChildElement("processSku", "ser");
        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("processSku");
        SOAPElement soapBodyElem2 = soapBodyElem1.addChildElement("skuName");
        soapBodyElem2.addTextNode("Sku" + number);
        SOAPElement soapBodyElem3 = soapBodyElem1.addChildElement("skuDescription");
        soapBodyElem3.addTextNode(description + System.currentTimeMillis() + " for Sku" + number);

        MimeHeaders headers = soapMessage.getMimeHeaders();
        //headers.addHeader("SOAPAction", serverURI);

        soapMessage.saveChanges();

        /* Print the request message */
        System.out.print("Request SOAP Message = ");
        soapMessage.writeTo(System.out);
        System.out.println();

        return soapMessage;
    }

    /**
     * Method used to print the SOAP Response
     */
    private static void printSOAPResponse(SOAPMessage soapResponse) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        Source sourceContent = soapResponse.getSOAPPart().getContent();
        System.out.print("\nResponse SOAP Message = ");
        StreamResult result = new StreamResult(System.out);
        transformer.transform(sourceContent, result);
    }

}