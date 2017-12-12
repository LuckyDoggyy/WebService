package com.we.ws.admin.flow.match.OwlHandle;


import org.apache.jena.ontology.*;
import org.apache.jena.ontology.OntDocumentManager;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;

import java.util.ArrayList;
import java.util.Scanner;


/*
 * Created by xuxyu on 2017/8/7.
 */

public class OntologySearch {

    OntModel model;
    OntDocumentManager documentManager;
    public final int MAX_DISTANCE = 10;

    public OntologySearch() throws Exception{
        model = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
        documentManager = model.getDocumentManager();
        addAllEntry();
    }

    public void addAltEntry(String source) throws Exception{
            documentManager.addAltEntry(source,null);
            model.read(source,"RDF/XML");
    }

    public ArrayList<OntClass> getSuperClasses(OntClass oc){
        ArrayList<OntClass> ocList = new ArrayList<>();
        ocList.add(oc);
        while(oc.hasSuperClass()){
            oc = oc.getSuperClass();
            ocList.add(oc);
        }
        return ocList;
    }

    public ArrayList<OntClass> getSubClasses(OntClass oc){
        ArrayList<OntClass> ocList = new ArrayList<>();
        ocList.add(oc);
        while(oc.hasSubClass()){
            oc = oc.getSubClass();
            ocList.add(oc);
        }
        return ocList;
    }

/*    public int getSuperDistance(OntClass c1, OntClass c2) {
        String c1uri = c1.getURI();
        String c2uri = c2.getURI();
        int distance = 0;
        while(c1.hasSuperClass()){
            c1uri = c1.getSuperClass().getURI();
            if(!c1uri.equals(c2uri))
                distance ++;
        }
        return distance;
    }*/

    public int ontologyDistance(OntClass c1, OntClass c2){
        if(c1.getLocalName().equals(c2.getLocalName()))
            return 0;
        ArrayList<OntClass> c1List = getSuperClasses(c1);
        ArrayList<OntClass> c2List = getSuperClasses(c2);
        for(int i = 0 ; i < c1List.size() ; i ++)
            for(int j = 0 ; j < c2List.size() ; j ++)
                if(c1List.get(i).getLocalName().equals(c2List.get(j).getLocalName()))
                    return j + i;
        return MAX_DISTANCE;
    }

//    public /*ArrayList<String>*/ void getOwlFromFile() throws Exception {
//        File file = new File("D:\\web.txt");
//        InputStreamReader isr = new InputStreamReader(new FileInputStream(file),"utf-8");
//        BufferedReader br = new BufferedReader(isr);
//        String line = null;
//        while((line =br.readLine()) != null){
//            documentManager.addAltEntry(line,null);
//            model.read(line,"RDF/XML");
//            owlList.add(line);
//        }
//        return owlList;
//    }

/*    public void addAllOwl() throws Exception {
        ArrayList<String> owlList = getOwlFromFile();
        for (int i = 0; i < owlList.size(); i++)
            addAltEntry(owlList.get(i));
    }*/

    public boolean isSub(String ontStr1, String ontStr2) {
        if(!ontStr1.substring(0,ontStr1.indexOf("#") - 1).equals(ontStr2.substring(0,ontStr2.indexOf("#") - 1)))
            return false;
        OntClass ont1 = model.getOntClass(ontStr1);
        OntClass ont2 = model.getOntClass(ontStr2);
        ArrayList<OntClass> ontList = getSubClasses(ont2);
        for(int i = 0 ; i < ontList.size() ; i ++)
            if(ontList.get(i).getLocalName().equals(ont1.getLocalName()))
                return true;
        return false;
    }

    public void addAllEntry() throws Exception{
        addAltEntry("http://127.0.0.1/ontology/ActorDefault.owl");
        addAltEntry("http://127.0.0.1/ontology/ApothecaryOntology.owl");
        addAltEntry("http://127.0.0.1/ontology/Country.owl");
        addAltEntry("http://127.0.0.1/ontology/EMAOntology.owl");
        addAltEntry("http://127.0.0.1/ontology/EmergencyPhysicianOntology.owl");
        addAltEntry("http://127.0.0.1/ontology/Expression.owl");
        addAltEntry("http://127.0.0.1/ontology/Grounding.owl");
        addAltEntry("http://127.0.0.1/ontology/HealthInsuranceOntology.owl");
        addAltEntry("http://127.0.0.1/ontology/HospitalPhysicianOntology.owl");
        addAltEntry("http://127.0.0.1/ontology/HospitalReceptionOntology.owl");
        addAltEntry("http://127.0.0.1/ontology/MedicalFlightCompanyOntology.owl");
        addAltEntry("http://127.0.0.1/ontology/MedicalTransportCompanyOntology.owl");
        addAltEntry("http://127.0.0.1/ontology/Mid-level-ontology.owl");
        addAltEntry("http://127.0.0.1/ontology/NonMedicalFlightCompanyOntology.owl");
        addAltEntry("http://127.0.0.1/ontology/NonMedicalTransportCompanyOntology.owl");
        addAltEntry("http://127.0.0.1/ontology/ObjectList.owl");
        addAltEntry("http://127.0.0.1/ontology/PDDLExpression.owl");
        addAltEntry("http://127.0.0.1/ontology/PatientOntology.owl");
        addAltEntry("http://127.0.0.1/ontology/Process.owl");
        addAltEntry("http://127.0.0.1/ontology/Profile.owl");
        addAltEntry("http://127.0.0.1/ontology/ProfileDeprecatedElements.owl");
        addAltEntry("http://127.0.0.1/ontology/SUMO.owl");
        addAltEntry("http://127.0.0.1/ontology/Service.owl");
        addAltEntry("http://127.0.0.1/ontology/ShoppingCart.owl");
        addAltEntry("http://127.0.0.1/ontology/TravelMessageOntology.owl");
        addAltEntry("http://127.0.0.1/ontology/Units.owl");
        addAltEntry("http://127.0.0.1/ontology/books.owl");
        addAltEntry("http://127.0.0.1/ontology/concept.owl");
        addAltEntry("http://127.0.0.1/ontology/core-plus-office.owl");
        addAltEntry("http://127.0.0.1/ontology/extendedCamera.owl");
        addAltEntry("http://127.0.0.1/ontology/finance_th_web.owl");
        addAltEntry("http://127.0.0.1/ontology/geographydataset.owl");
        addAltEntry("http://127.0.0.1/ontology/messemodul.owl");
        addAltEntry("http://127.0.0.1/ontology/my_ontology.owl");
        addAltEntry("http://127.0.0.1/ontology/om2-1.owl");
        addAltEntry("http://127.0.0.1/ontology/ontosem.owl");
        addAltEntry("http://127.0.0.1/ontology/order.owl");
        addAltEntry("http://127.0.0.1/ontology/portal.owl");
        addAltEntry("http://127.0.0.1/ontology/protons.owl");
        addAltEntry("http://127.0.0.1/ontology/protont.owl");
        addAltEntry("http://127.0.0.1/ontology/protonu.owl");
        addAltEntry("http://127.0.0.1/ontology/simplified_sumo.owl");
        addAltEntry("http://127.0.0.1/ontology/spatial_ontology.owl");
        addAltEntry("http://127.0.0.1/ontology/support.owl");
        addAltEntry("http://127.0.0.1/ontology/technical.owl");
        addAltEntry("http://127.0.0.1/ontology/time-entry.owl");
        addAltEntry("http://127.0.0.1/ontology/travel.owl");
        addAltEntry("http://127.0.0.1/ontology/univ-bench.owl");
    }

    public OntClass getOntClass(String ontString){
        return model.getOntClass(ontString);
    }

    public static void main(String [] args) throws Exception {
        OntologySearch os = new OntologySearch();
        String str1, str2;
        Scanner scanner = new Scanner(System.in);
        for(;;) {
            System.out.println("Input the first ontology: ");
            str1 = scanner.nextLine().trim();
            System.out.println("Input the second ontology: ");
            str2 = scanner.nextLine().trim();
            OntClass oc1 = os.getOntClass(str1);
            OntClass oc2 = os.getOntClass(str2);
            System.out.println(os.getSuperClasses(oc1));
            System.out.println(os.getSuperClasses(oc2));
            System.out.println(oc1.getLocalName() + " " + oc1.getURI());
            while(oc1.hasSuperClass()){
                oc1 = oc1.getSuperClass();
                System.out.println(oc1.getLocalName() + " " + oc1.getURI());
            }
            System.out.println(oc2.getLocalName() + " " + oc2.getURI());
            while(oc2.hasSuperClass()){
                oc2 = oc2.getSuperClass();
                System.out.println(oc2.getLocalName() + " " + oc2.getURI());
            }
            System.out.println(os.isSub(str1, str2));


        }





        /*os.addAltEntry("http://127.0.0.1/ontology/ActorDefault.owl");
        os.addAltEntry("http://127.0.0.1/ontology/ApothecaryOntology.owl");
        os.addAltEntry("http://127.0.0.1/ontology/Country.owl");
        os.addAltEntry("http://127.0.0.1/ontology/EMAOntology.owl");
        os.addAltEntry("http://127.0.0.1/ontology/EmergencyPhysicianOntology.owl");
        os.addAltEntry("http://127.0.0.1/ontology/Expression.owl");
        os.addAltEntry("http://127.0.0.1/ontology/Grounding.owl");
        os.addAltEntry("http://127.0.0.1/ontology/HealthInsuranceOntology.owl");
        os.addAltEntry("http://127.0.0.1/ontology/HospitalPhysicianOntology.owl");
        os.addAltEntry("http://127.0.0.1/ontology/HospitalReceptionOntology.owl");
        os.addAltEntry("http://127.0.0.1/ontology/MedicalFlightCompanyOntology.owl");
        os.addAltEntry("http://127.0.0.1/ontology/MedicalTransportCompanyOntology.owl");
        os.addAltEntry("http://127.0.0.1/ontology/Mid-level-ontology.owl");
        os.addAltEntry("http://127.0.0.1/ontology/NonMedicalFlightCompanyOntology.owl");
        os.addAltEntry("http://127.0.0.1/ontology/NonMedicalTransportCompanyOntology.owl");
        os.addAltEntry("http://127.0.0.1/ontology/ObjectList.owl");
        os.addAltEntry("http://127.0.0.1/ontology/PDDLExpression.owl");
        os.addAltEntry("http://127.0.0.1/ontology/PatientOntology.owl");
        os.addAltEntry("http://127.0.0.1/ontology/Process.owl");
        os.addAltEntry("http://127.0.0.1/ontology/Profile.owl");
        os.addAltEntry("http://127.0.0.1/ontology/ProfileDeprecatedElements.owl");
        os.addAltEntry("http://127.0.0.1/ontology/SUMO.owl");
        os.addAltEntry("http://127.0.0.1/ontology/Service.owl");
        os.addAltEntry("http://127.0.0.1/ontology/ShoppingCart.owl");
        os.addAltEntry("http://127.0.0.1/ontology/TravelMessageOntology.owl");
        os.addAltEntry("http://127.0.0.1/ontology/Units.owl");
        os.addAltEntry("http://127.0.0.1/ontology/books.owl");
        os.addAltEntry("http://127.0.0.1/ontology/concept.owl");
        os.addAltEntry("http://127.0.0.1/ontology/core-plus-office.owl");
        os.addAltEntry("http://127.0.0.1/ontology/extendedCamera.owl");
        os.addAltEntry("http://127.0.0.1/ontology/finance_th_web.owl");
        os.addAltEntry("http://127.0.0.1/ontology/geographydataset.owl");
        os.addAltEntry("http://127.0.0.1/ontology/messemodul.owl");
        os.addAltEntry("http://127.0.0.1/ontology/my_ontology.owl");
        os.addAltEntry("http://127.0.0.1/ontology/om2-1.owl");
        os.addAltEntry("http://127.0.0.1/ontology/ontosem.owl");
        os.addAltEntry("http://127.0.0.1/ontology/order.owl");
        os.addAltEntry("http://127.0.0.1/ontology/portal.owl");
        os.addAltEntry("http://127.0.0.1/ontology/protons.owl");
        os.addAltEntry("http://127.0.0.1/ontology/protont.owl");
        os.addAltEntry("http://127.0.0.1/ontology/protonu.owl");
        os.addAltEntry("http://127.0.0.1/ontology/simplified_sumo.owl");
        os.addAltEntry("http://127.0.0.1/ontology/spatial_ontology.owl");
        os.addAltEntry("http://127.0.0.1/ontology/support.owl");
        os.addAltEntry("http://127.0.0.1/ontology/technical.owl");
        os.addAltEntry("http://127.0.0.1/ontology/time-entry.owl");
        os.addAltEntry("http://127.0.0.1/ontology/travel.owl");
        os.addAltEntry("http://127.0.0.1/ontology/univ-bench.owl");*/
    }

}
