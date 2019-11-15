/**
 * LDAPDataService_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package top.hting.wsdl4a;

public interface LDAPDataService_PortType extends java.rmi.Remote {
    public String userQueryServices_userQuery4Detail(String arg0) throws java.rmi.RemoteException;
    public String userQueryServices_userQueryByCondition(String arg0) throws java.rmi.RemoteException;
    public String userQueryServices_userQueryRoles(String arg0) throws java.rmi.RemoteException;
    public String orgQueryServices_queryOrg4Detail(String arg0) throws java.rmi.RemoteException;
    public String orgQueryServices_queryOrgByCondition(String arg0) throws java.rmi.RemoteException;
    public String deptQueryServices_queryDeptByCondition(String arg0) throws java.rmi.RemoteException;
    public String deptQueryServices_queryDept4Detail(String arg0) throws java.rmi.RemoteException;
    public String roleQueryServices_roleQuery4Detail(String arg0) throws java.rmi.RemoteException;
    public String roleQueryServices_roleQueryByCondition(String arg0) throws java.rmi.RemoteException;
    public String roleQueryServices_roleQueryUsersList(String arg0) throws java.rmi.RemoteException;
}
