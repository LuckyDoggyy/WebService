package com.we.ws.admin.flow.match.OwlHandle;

import org.apache.jena.ontology.*;
import org.apache.jena.ontology.OntDocumentManager;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.iterator.ExtendedIterator;

import java.util.Iterator;

/**
 * Created by xuxyu on 2017/8/1.
 */
public class ParseOwl {



    private static void printOntClassNode(OntClass oc,int depth)
    {
        System.out.println("OntClass:"+oc.getLocalName()+" Depth:"+depth);
        //print all subclass
        if(oc.hasSubClass())
        {
            for(Iterator<OntClass> it = oc.listSubClasses(true); it.hasNext();){
                OntClass c = it.next();
                printOntClassNode(c, depth+1);
            }
        }
        //print all Individual
        for(Iterator<Individual> i = (Iterator<Individual>) oc.listInstances(true); i.hasNext();){
            Individual ind = i.next();
            int deeper=depth+1;
            System.out.println("Individual:"+ind.getLocalName()+" Depth:"+deeper);
        }
        return ;
    }

    private static void printSuperClass(OntClass oc) {
        System.out.println("OntClass:" + oc.getLocalName());
        while(oc.hasSuperClass()){
            oc = oc.getSuperClass();
            System.out.println("OntClass:" + oc.getLocalName());
        }
    }


    public static void main(String[] args) {
        // create the base model
        String SOURCE = "http://127.0.0.1/ontology/travel.owl";  // http://127.0.0.1/ontology/travel.owl
        String NS = SOURCE + "#";
        OntModel m = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM );
        OntDocumentManager dm = m.getDocumentManager();
        dm.addAltEntry( SOURCE,null);
        m.read( SOURCE, "RDF/XML" );

        //get root node TRAVEL
        OntClass rootClass=m.getOntClass(NS + "Destination");   // Destination
        System.out.println(rootClass.getLocalName());
//        int depth=0;
//        printOntClassNode(rootClass,depth);

        System.out.println();

//        printSuperClass(m.getOntClass(NS+ "Shipper"));  // Capital
//        System.out.println();

//        OntClass capital = m.getOntClass(NS + "Capital");
//        printSuperClass(capital);
//        System.out.println();
//
//        OntClass citySuper = m.getOntClass(NS + "City");
//        System.out.println(citySuper.getSuperClass().getLocalName());
//
//        OntClass urbanArea = m.getOntClass(NS + "UrbanArea");
//        printSuperClass(urbanArea);
//        System.out.println();






        //列出model中所包含的所有的类
        for(Iterator<OntClass> i = m.listClasses(); i.hasNext();){
            OntClass c = i.next();
            System.out.println("ontClass:" + c.getLocalName());
        }

        System.out.println();

        //理出model中所包含的所有的实例
        for(Iterator<Individual> i = m.listIndividuals(); i.hasNext();){
            Individual j = i.next();
            System.out.println("individual:" + j.getLocalName());
        }

        System.out.println();

        //列出model中所有的物体的属性
        for(ExtendedIterator<ObjectProperty> i = m.listObjectProperties(); i.hasNext();){
            System.out.println("ObjectProperty:" + i.next().getLocalName());
        }

        System.out.println();

        //列出model中所有的数据类型属性
        for(ExtendedIterator<DatatypeProperty> i = m.listDatatypeProperties(); i.hasNext();){
            System.out.println("DatatypeProperty:" + i.next().getLocalName());
        }

    }
}
