/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.university.datamining.algorithms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;
import hu.university.datamining.algorithms.Node;

public final class Eclat extends Algorithm {
	
    private LinkedList<Node> result=new LinkedList<Node>();

    @Override
    public LinkedList<Node> execute(String fileName, String columnDelimiter, String dataDelimiter, int minSupport) {
       this.fileName=fileName;
       this.minSupport=minSupport;
       BufferedReader br=null;
       try{
			File f = new File(fileName);
			br=new BufferedReader(new FileReader(f));

			String sCurrentLine=br.readLine();
			StringTokenizer st=new StringTokenizer(sCurrentLine,columnDelimiter);
			while(st.hasMoreElements())
			{
				ItNode in=new ItNode();
				in.getName().add(st.nextToken());
				itNodes.add(in);
			}
			while((sCurrentLine=br.readLine())!=null)
			{
				StringTokenizer st2=new StringTokenizer(sCurrentLine,dataDelimiter);
			//	System.out.println(st2.countTokens());
				for(ItNode in : this.itNodes)
				{
					int nextValue=Integer.valueOf(st2.nextToken()).intValue();
					if(nextValue==1)
						in.getBa().add(true);
					else
						in.getBa().add(false);
				}
			}
			this.count();
			this.eclat();
		}catch(IOException io)
		{
			System.out.println(io.getMessage());
		}
		finally
		{
			try{
			if(br!=null)
			br.close();
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
		}
       return result;
    }
	
	protected class ItNode extends Node{
		LinkedList<ItNode> children=new LinkedList<ItNode>();
		ItNode parent;

		void addChild(ItNode attr)
		{
		        attr.parent=this;
		        this.children.add(attr);
		}

		ItNode getFirstChild()
		{
		        if(children.size()>0)
		        {
			        ItNode first=children.peekFirst();
			        children.remove();
			        return first;
		        }
		        else
		        {
		        	return null;
		        }
		}
		}
	
	protected void count()
	{
		int support;
		for(ItNode it : itNodes)
		{
			support=0;
			Vector<Boolean> v=it.getBa();
			for(Boolean b : v)
			{
				if(b.booleanValue()==true)
					support++;
			}
			it.setSupportCount(support);
		}
	}
	
	 Eclat()
	{}

	protected LinkedList<ItNode> itNodes = new LinkedList<ItNode>();
	
	protected ItNode supportCount(ItNode in)
	{
		int support=0;
		for(Boolean b : in.getBa())
		{
			if(b.booleanValue()==true)
				support++;
		}
		in.setSupportCount(support);
		return in;
	}
	
	protected ItNode getCandidate(ItNode curr, ItNode other)
	{
		ItNode newItNode = new ItNode();
		newItNode.getName().addAll(curr.getName());
		for(int i=0;i<curr.getName().size();i++)
		{
			if(!curr.getName().contains(other.getName().get(i)))
			{
				newItNode.getName().add(other.getName().get(i));
			}
		}
		for(int i=0;i<curr.getBa().size();i++)
		{
			if(curr.getBa().get(i).booleanValue()==other.getBa().get(i) && other.getBa().get(i)==true)
			{
				newItNode.getBa().add(true);
			}
			else
				newItNode.getBa().add(false);
		}
		newItNode=supportCount(newItNode);
		newItNode.setConfidence((float)newItNode.getSupportCount()/(float)curr.getSupportCount());
		newItNode.setRule(curr.name+" => "+newItNode.name);
		if(newItNode.getSupportCount()>=minSupport)
		{
			return newItNode;
		}
		else
			return null;
	}
	
	protected void extend(ItNode curr)
	{
		for(int i=0;i<curr.parent.children.size();i++)
		{
			ItNode candidate=getCandidate(curr,curr.parent.children.get(i));
//			System.out.println(curr.name+";"+curr.parent.children.get(i).name);
			if(candidate!=null)
			{
				curr.addChild(candidate);
			}
		}
		
		while(!curr.children.isEmpty())
		{
			ItNode child=curr.getFirstChild();
			if(child==null)
				break;
			extend(child);
			save(child);
//			delete(child);
		}
	}
	
	protected void save(ItNode in)
	{
		this.result.add(in);
	}
	
	public LinkedList<Node> getResult() {
		return result;
	}
	
	protected void eclat()
	{
		ItNode root = new ItNode();
		root.getName().add("root");
		this.count();
		for(ItNode itnode : itNodes)
		{
			if(itnode.getSupportCount()>=minSupport)
			{
				root.addChild(itnode);
			}
		}
		while(!root.children.isEmpty())
		{
			 ItNode child=root.getFirstChild();
             if(child==null)
             break;
             extend(child);
             save(child);
            // delete(child);
		}
	}

}

