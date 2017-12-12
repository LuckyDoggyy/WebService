/*
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.we.ws.admin.flow.match.OwlsHandle;

import org.mindswap.common.Variable;
import org.mindswap.owl.*;
import org.mindswap.owls.OWLSFactory;
import org.mindswap.owls.process.AtomicProcess;
import org.mindswap.owls.process.execution.DefaultProcessMonitor;
import org.mindswap.owls.process.execution.ProcessExecutionEngine;
import org.mindswap.owls.process.variable.Input;
import org.mindswap.owls.process.variable.Output;
import org.mindswap.owls.service.Service;
import org.mindswap.query.ValueMap;
import org.mindswap.utils.Utils;

import java.net.URI;

public class RunService {
    Service service;
    Process process;
    String inValue;
    String outValue;
    ValueMap<Input, OWLValue> inputs;
    ValueMap<Output, OWLValue> outputs;
    ProcessExecutionEngine exec = OWLSFactory.createExecutionEngine();

    public RunService() {
        this.exec.addMonitor(new DefaultProcessMonitor());
    }

    public void runZipCode() throws Exception {
        OWLKnowledgeBase kb = OWLFactory.createKB();
        this.service = kb.readService(URI.create("http://on.cs.unibas.ch/owl-s/1.2/ZipCodeFinder.owl"));
        this.process = this.service.getProcess();
        this.inputs = new ValueMap();
        this.inputs.setValue(this.process.getInput("City"), kb.createDataValue("College Park"));
        this.inputs.setValue(this.process.getInput("State"), kb.createDataValue("MD"));
        this.outputs = this.exec.execute(this.process, this.inputs, kb);
        OWLIndividual out = this.outputs.getIndividualValue(this.process.getOutput());
        System.out.println("Executed service '" + this.service + "'");
        System.out.println("Grounding WSDL: " + ((AtomicProcess)this.process).getGrounding().getDescriptionURL());
        System.out.println("City   = College Park");
        System.out.println("State  = MD");
        System.out.println("Output = ");
        System.out.println(Utils.formatRDF(out.toRDF(true, true)));
        System.out.println();
    }

    public void runJGroundingTest() throws Exception {
        OWLKnowledgeBase kb = OWLFactory.createKB();
        this.service = kb.readService(URI.create("http://on.cs.unibas.ch/owl-s/1.2/JGroundingTest.owl"));
        this.process = this.service.getProcess();
        this.inputs = new ValueMap();
        this.inputs.setValue(this.process.getInput("FirstParam"), kb.createDataValue("2"));
        this.inputs.setValue(this.process.getInput("SecParam"), kb.createDataValue("3"));
        this.outputs = this.exec.execute(this.process, this.inputs, kb);
    }

    public void runBookFinder() throws Exception {
        OWLKnowledgeBase kb = OWLFactory.createKB();
        this.service = kb.readService(URI.create("http://on.cs.unibas.ch/owl-s/1.2/BookFinder.owl"));
        this.process = this.service.getProcess();
        this.inputs = new ValueMap();
        this.inValue = "City of Glass";
        this.inputs.setValue(this.process.getInput("BookName"), kb.createDataValue(this.inValue));
        this.outputs = this.exec.execute(this.process, this.inputs, kb);
        OWLIndividual out = this.outputs.getIndividualValue(this.process.getOutput());
        System.out.println("Executing OWL-S service " + this.service);
        System.out.println("Grounding WSDL: " + ((AtomicProcess)this.process).getGrounding().getDescriptionURL());
        System.out.println("BookName = " + this.inValue);
        System.out.println("BookInfo = ");
        System.out.println(Utils.formatRDF(out.toRDF(true, true)));
        System.out.println();
    }

    public void runBookPrice() throws Exception {
        OWLKnowledgeBase kb = OWLFactory.createKB();
        this.service = kb.readService(URI.create("http://on.cs.unibas.ch/owl-s/1.2/BookPrice.owl"));
        this.process = this.service.getProcess();
        this.inputs = new ValueMap();
        this.inValue = "City of Glass";
        this.inputs.setValue(this.process.getInput("BookName"), kb.createDataValue(this.inValue));
        this.inputs.setValue(this.process.getInput("Currency"), kb.getIndividual(URI.create("http://on.cs.unibas.ch/owl/currency.owl#EUR")));
        this.outputs = this.exec.execute(this.process, this.inputs, kb);
        OWLIndividual out = this.outputs.getIndividualValue(this.process.getOutput());
        System.out.println("Executed service " + this.service);
        System.out.println("Book Name = " + this.inValue);
        System.out.println("Price = ");
        System.out.println(Utils.formatRDF(out.toRDF(true, true)));
        System.out.println();
    }

    public void runFindCheaperBook() throws Exception {
        OWLKnowledgeBase kb = OWLFactory.createKB();
        this.exec.getExecutionValidator().setPreconditionCheck(true);
        this.service = kb.readService(URI.create("http://on.cs.unibas.ch/owl-s/1.2/FindCheaperBook.owl"));
        this.process = this.service.getProcess();
        this.inputs = new ValueMap();
        this.inputs.setValue(this.process.getInput("BookName"), kb.createDataValue("City of Glass"));
        this.outputs = this.exec.execute(this.process, this.inputs, kb);
        OWLIndividual price = this.outputs.getIndividualValue(this.process.getOutput("BookPrice"));
        String bookstore = this.outputs.getStringValue(this.process.getOutput("Bookstore"));
        System.out.println("Executed service " + this.service);
        System.out.println("Bookstore = " + bookstore);
        System.out.println("Price = ");
        System.out.println(Utils.formatRDF(price.toRDF(true, true)));
        System.out.println();
    }

    public void runCurrencyConverter() throws Exception {
        OWLKnowledgeBase kb = OWLFactory.createKB();
        this.service = kb.readService(URI.create("http://on.cs.unibas.ch/owl-s/1.2/CurrencyConverter.owl"));
        this.process = this.service.getProcess();
        this.inputs = new ValueMap();
        OWLIndividual EUR = kb.getIndividual(URI.create("http://on.cs.unibas.ch/owl/currency.owl#EUR"));
        this.inputs.setValue(this.process.getInput("OutputCurrency"), EUR);
        OWLIndividual USD = kb.getIndividual(URI.create("http://on.cs.unibas.ch/owl/currency.owl#USD"));
        OWLClass Price = kb.getClass(URI.create("http://on.cs.unibas.ch/owl/concepts.owl#Price"));
        OWLObjectProperty currency = kb.getObjectProperty(URI.create("http://on.cs.unibas.ch/owl/concepts.owl#currency"));
        OWLDataProperty amount = kb.getDataProperty(URI.create("http://on.cs.unibas.ch/owl/concepts.owl#amount"));
        OWLIndividual inputPrice = kb.createInstance(Price, (URI)null);
        inputPrice.addProperty(currency, USD);
        inputPrice.addProperty(amount, "100");
        System.out.println(inputPrice.toRDF(true, true));
        this.inputs.setValue(this.process.getInput("InputPrice"), inputPrice);
        this.outputs = this.exec.execute(this.process, this.inputs, kb);
        OWLIndividual out = this.outputs.getIndividualValue(this.process.getOutput());
        System.out.println("Executed service " + this.service);
        System.out.println("Grounding WSDL: " + ((AtomicProcess)this.process).getGrounding().getDescriptionURL());
        System.out.println("Input  = ");
        System.out.println(Utils.formatRDF(inputPrice.toRDF(true, true)));
        System.out.println("Output = ");
        System.out.println(Utils.formatRDF(out.toRDF(true, true)));
        System.out.println();
    }

    public void runFrenchDictionary() throws Exception {
        OWLKnowledgeBase kb = OWLFactory.createKB();
        kb.setReasoner("Pellet");
        this.service = kb.readService(URI.create("http://on.cs.unibas.ch/owl-s/1.2/FrenchDictionary.owl"));
        this.process = this.service.getProcess();
        this.inputs = new ValueMap();
        this.inValue = "mere";
        this.inputs.setValue(this.process.getInput("InputString"), kb.createDataValue(this.inValue));
        this.outputs = this.exec.execute(this.process, this.inputs, kb);
        this.outValue = this.outputs.getValue((Variable)this.process.getOutputs().getIndividual("OutputString")).toString();
        System.out.println("Executed service " + this.service);
        System.out.println("Input  = " + this.inValue);
        System.out.println("Output = " + this.outValue);
        System.out.println();
    }

    public void runTranslator() throws Exception {
        OWLKnowledgeBase kb = OWLFactory.createKB();
        kb.setReasoner("RDFS");
        this.service = kb.readService(URI.create("http://on.cs.unibas.ch/owl-s/1.2/BabelFishTranslator.owl"));
        this.process = this.service.getProcess();
        OWLIndividual English = kb.getIndividual(URI.create("http://on.cs.unibas.ch/owl/2003/09/factbook/languages.rdf#English"));
        OWLIndividual French = kb.getIndividual(URI.create("http://on.cs.unibas.ch/owl/2003/09/factbook/languages.rdf#French"));
        this.inputs = new ValueMap();
        this.inputs.setValue(this.process.getInput("InputString"), kb.createDataValue("Hello world!"));
        this.inputs.setValue(this.process.getInput("InputLanguage"), English);
        this.inputs.setValue(this.process.getInput("OutputLanguage"), French);
        this.outputs = this.exec.execute(this.process, this.inputs, kb);
        this.outValue = this.outputs.getValue(this.process.getOutput()).toString();
        System.out.println("Executed service '" + this.service + "'");
        System.out.println("Grounding WSDL: " + ((AtomicProcess)this.process).getGrounding().getDescriptionURL());
        System.out.println("Output = " + this.outValue);
        System.out.println();
    }

    public void runDictionary() throws Exception {
        OWLKnowledgeBase kb = OWLFactory.createKB();
        this.service = kb.readService(URI.create("http://127.0.0.1/domains/1.1/travel/citycountry_hotel_service.owls"));
        this.process = this.service.getProcess();
        this.inputs = new ValueMap();
        this.inputs.setValue(this.process.getInput("_COUNTRY"), kb.createDataValue("http://127.0.0.1/ontology/portal.owl#Country"));
        this.inputs.setValue(this.process.getInput("_CITY"), kb.createDataValue("http://127.0.0.1/ontology/portal.owl#City"));
        this.outputs = this.exec.execute(this.process, this.inputs, kb);
        OWLDataValue out = (OWLDataValue)this.outputs.getValue(this.process.getOutput());
        System.out.println("Executed service '" + this.service + "'");
        System.out.println("Grounding WSDL: " + ((AtomicProcess)this.process).getGrounding().getDescriptionURL());
        System.out.println("Input  = " + this.inValue);
        System.out.println("Output = " + out.toString());
        System.out.println();
    }

    public void runAll() throws Exception {
        try {
            this.runDictionary();
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }

    public static void main(String[] args) throws Exception {
        RunService test = new RunService();
        test.runAll();
    }
}
*/
