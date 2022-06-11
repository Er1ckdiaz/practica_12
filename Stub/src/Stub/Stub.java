/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stub;

import java.rmi.Remote;
import java.util.Vector;


public interface Stub extends Remote{
    public Vector<Vector<String>> Dcoor(String cood)
            throws java.rmi.RemoteException;
    public Vector<Vector<String>> Dcuad(String CoordX, String CoordY) 
            throws java.rmi.RemoteException;
    
}
