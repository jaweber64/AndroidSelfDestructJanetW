package com.example;

// Janet Weber 9/24/15
// Self-Destruct Code : Lab Assignment (Week 6)
// Design the code to be installed in the Star Ship Enterprise
// that will initiate the self destruct sequence. 

import java.util.Scanner;

public class SelfDestructClass {
   // enum code {CODE_ONE, CODE_TWO, CODE_THREE}

    public static void main (String[] args) {

        //****************************************************************************
        // VARIABLES AND CONSTANTS
        //***************************************************************************
        // String constants for people in command - those providing codes
        final String CAPTAIN = "James T. Kirk";
        final String FIRST_OFFICER = "Dr. Spock";
        final String CHIEF_ENGINEER = "Lieutenant Commander, Scott";

        // String constants for correct code sequences
        final String CODE_ONE = "11A";
        final String CODE_TWO = "11A2B";
        final String CODE_THREE = "1B2B3";
        final String FINAL_CODE = "000 DESTRUCT 0";

        // Boolean variables
        Boolean identityVerified = false,         // is speaker identity verified
                codeVerified = false,             // is code verified
                done = false;                     // are we done (early).

        // String variables
        String communOfficer = "Uhura",           // The comunications officer
            codeInput;                            // variable to hold code input from console

        Scanner scan = new Scanner(System.in);    // Use this to get input from console
        String speaker;                           // variable to hold identity input from console

        //*******************************************************************************
        // Print messages to console and make sure computer is connected to bridge.
        //*******************************************************************************
        // Output to console the program purpose
        System.out.println("Starfleet Order two-zero-zero-five (Self-Destruct order)");
        System.out.println("");

        // The first step is to connect the main computer to the bridge.  The commanding officer
        // must direct the communications officer to perform this duty.
        // Prompt user and get input from console.
        System.out.print("Connect Main computer to the bridge. Who gives this order (commanding officer) => ");
        speaker = scan.nextLine();
        // Make sure identity is that of the Captain.
        if (speaker.matches(CAPTAIN)){
            // speaker has been verified, proceed with prompting for next speaker
            System.out.print("Order verified. Who implements this order (communications officer) => ");
            speaker = scan.nextLine();
            if (speaker.matches(communOfficer)){
                // speaker has been verified - print msg to console
                System.out.println("Activation complete. Main computer now accessible from the bridge");
                System.out.println("");
            } else {
                // speaker is not Communications Officer so stop self destruct.
                done = true;
            }
        } else {
            // speaker is NOT Captain so stop self destruct.
            done = true;
        }

        //**********************************************************************************
        // Only get here if computer and bridge are connected (done = false).  Ready for 3 codes
        //*********************************************************************************
        if (!done) {
            // Notify console that computer is ready for the code sequences
            System.out.println("Computer will initialize self-destruct sequence. Ready to receive code sequences.");
            codeInput = "";                                     // initialize variable codeInput
            for (int codeNum = 1; codeNum <= 3; codeNum++) {    // 3 times - once for each code
                System.out.print("Identify yourself => ");              // get identity of speaker
                speaker = scan.nextLine();
                switch (codeNum) {                                      // switch through each of 3 codes
                    case 1:                                             // Captain must provide code sequence 1
                        if (speaker.matches(CAPTAIN)) {                         // if correct speaker
                            identityVerified = true;                            // set boolean
                            System.out.print("Awaiting self-destruct code sequence one => "); // get first code
                            codeInput = scan.nextLine();
                            if (codeInput.matches(CODE_ONE)) {                                // if correct code set
                                codeVerified = true;                                          //  boolean
                            }
                        }
                        break;
                    case 2:                                             // First officer must provide code sequence 2
                        if (speaker.matches(FIRST_OFFICER)) {                   // if correct speaker
                            identityVerified = true;                            // set boolean
                            System.out.print("Awaiting self-destruct code sequence two => "); // get 2nd code
                            codeInput = scan.nextLine();
                            if (codeInput.matches(CODE_TWO)) {                                // if correct code set
                                codeVerified = true;                                          //  boolean
                            }
                        }
                        break;
                    case 3:                                             // Chief engineer must provide code sequence 3
                        if (speaker.matches(CHIEF_ENGINEER)) {                  // if correct speaker
                            identityVerified = true;                            // set boolean
                            System.out.print("Awaiting self-destruct code sequence three => "); // get 3rd code
                            codeInput = scan.nextLine();
                            if (codeInput.matches(CODE_THREE)) {                                // if correct code
                                codeVerified = true;                                            // set boolean
                            }
                        }
                        break;
                    default:  // should be impossible to get here
                        identityVerified = false;
                        codeVerified = false;
                } // end of switch statement

                //*********************************************************************
                // code and identity have been input. If verified then inform console, otherwise
                // code or identity are invalid so inform console and quit.  When we get
                // to 3rd verified code, get final code and identity from console - also
                // verify final code and notify console.
                //*********************************************************************
                if ((identityVerified) && (codeVerified)) {
                    // correct speaker and code so notify console
                    System.out.println("Destruct sequence " + codeNum + ", code " + codeInput + " verified and correct.");
                    if (codeNum == 3) { // first 3 codes are good, one remaining
                        // ready for final code
                        System.out.println("");
                        System.out.println("Destruct sequence completed and engaged. Awaiting final code for 30 second count down");
                        System.out.print("Identify yourself => ");
                        speaker = scan.nextLine();
                        if (speaker.matches(CAPTAIN)) {
                            System.out.print("Awaiting FINAL self-destruct code sequence => ");
                            codeInput = scan.nextLine();
                            if (codeInput.matches(FINAL_CODE)) {
                                // BEGIN COUNTDOWN and count down from 30.
                                System.out.println("Countdown initialized.");
                                for (int i = 30; i > 0; i--) {
                                    System.out.println(i);
                                }
                                // END of COUNTDOWN - Destruction begins
                                System.out.println("DESTRUCTION UNDERWAY!  BOOM!!  EXPLOSION!!");
                            } else {
                                // incorrect final code so stop self-destruct sequence and break out of for loop
                                System.out.println("FINAL Code or NOT verified! Self-Destruct sequence NOT initiated.");
                                break;
                            } // end of if (codeInput.matches(FINAL_CODE))
                        } else {
                            // incorrect speaker identity - notify console and break out of for loop
                            System.out.println("Identity NOT verified! Self-Destruct sequence NOT initiated.");
                            break;
                        } // end of if (speaker.matches(CAPTAIN))
                    } // end of if (codeNum = 3)

                } else {
                    // incorrect identity (speaker) or incorrect code so notify console
                    System.out.println("Code or identity NOT verified! Self-Destruct sequence NOT initiated.");
                    break;   // done so break out of loop early
                }
                // reset booleans for next code sequence as we proceed through
                identityVerified = false;
                codeVerified = false;
            } // end of for loop

        }// end of if (!done)
    } // end of main()
} // end of selfDestructClass()
