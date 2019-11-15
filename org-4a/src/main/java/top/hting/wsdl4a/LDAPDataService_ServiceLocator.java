/**
 * LDAPDataService_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package top.hting.wsdl4a;

public class LDAPDataService_ServiceLocator extends org.apache.axis.client.Service implements top.hting.wsdl4a.LDAPDataService_Service {

    public LDAPDataService_ServiceLocator() {
    }


    public LDAPDataService_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public LDAPDataService_ServiceLocator(String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for LDAPDataServicePort
    private String LDAPDataServicePort_address = "http://msa-xtgl-soa1:8011/CommissionInvoke/proxy/LDAPService_BJproxy";

    public String getLDAPDataServicePortAddress() {
        return LDAPDataServicePort_address;
    }

    // The WSDD service name defaults to the port name.
    private String LDAPDataServicePortWSDDServiceName = "LDAPDataServicePort";

    public String getLDAPDataServicePortWSDDServiceName() {
        return LDAPDataServicePortWSDDServiceName;
    }

    public void setLDAPDataServicePortWSDDServiceName(String name) {
        LDAPDataServicePortWSDDServiceName = name;
    }

    public top.hting.wsdl4a.LDAPDataService_PortType getLDAPDataServicePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(LDAPDataServicePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getLDAPDataServicePort(endpoint);
    }

    public top.hting.wsdl4a.LDAPDataService_PortType getLDAPDataServicePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            top.hting.wsdl4a.LDAPDataServicePortBindingStub _stub = new top.hting.wsdl4a.LDAPDataServicePortBindingStub(portAddress, this);
            _stub.setPortName(getLDAPDataServicePortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setLDAPDataServicePortEndpointAddress(String address) {
        LDAPDataServicePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (top.hting.wsdl4a.LDAPDataService_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                top.hting.wsdl4a.LDAPDataServicePortBindingStub _stub = new top.hting.wsdl4a.LDAPDataServicePortBindingStub(new java.net.URL(LDAPDataServicePort_address), this);
                _stub.setPortName(getLDAPDataServicePortWSDDServiceName());
                return _stub;
            }
        }
        catch (Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        String inputPortName = portName.getLocalPart();
        if ("LDAPDataServicePort".equals(inputPortName)) {
            return getLDAPDataServicePort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://service.ldap.mgear.com/", "LDAPDataService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://service.ldap.mgear.com/", "LDAPDataServicePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(String portName, String address) throws javax.xml.rpc.ServiceException {

if ("LDAPDataServicePort".equals(portName)) {
            setLDAPDataServicePortEndpointAddress(address);
        }
        else
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
