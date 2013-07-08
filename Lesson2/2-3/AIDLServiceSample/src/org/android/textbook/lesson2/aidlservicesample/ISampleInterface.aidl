package org.android.textbook.lesson2.aidlservicesample;

interface ISampleInterface{
	oneway void doSomething(int i);
	void setString(in String s);
}