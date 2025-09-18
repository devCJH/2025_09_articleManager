package com.koreaIT.exam.controller;

import java.util.Scanner;

public abstract class Controller {
	public Scanner sc;
	public String cmd;
	
	public abstract void doAction(String methodName, String cmd);
}
