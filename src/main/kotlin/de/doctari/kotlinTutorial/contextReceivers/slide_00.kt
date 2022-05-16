package de.doctari.kotlinTutorial.contextReceivers

/*
                    ██╗  ██╗ ██████╗ ████████╗██╗     ██╗███╗   ██╗
                    ██║ ██╔╝██╔═══██╗╚══██╔══╝██║     ██║████╗  ██║
                    █████╔╝ ██║   ██║   ██║   ██║     ██║██╔██╗ ██║
                    ██╔═██╗ ██║   ██║   ██║   ██║     ██║██║╚██╗██║
                    ██║  ██╗╚██████╔╝   ██║   ███████╗██║██║ ╚████║
                    ╚═╝  ╚═╝ ╚═════╝    ╚═╝   ╚══════╝╚═╝╚═╝  ╚═══╝

                 ██████╗ ██████╗ ███╗   ██╗████████╗███████╗██╗  ██╗████████╗
                ██╔════╝██╔═══██╗████╗  ██║╚══██╔══╝██╔════╝╚██╗██╔╝╚══██╔══╝
                ██║     ██║   ██║██╔██╗ ██║   ██║   █████╗   ╚███╔╝    ██║
                ██║     ██║   ██║██║╚██╗██║   ██║   ██╔══╝   ██╔██╗    ██║
                ╚██████╗╚██████╔╝██║ ╚████║   ██║   ███████╗██╔╝ ██╗   ██║
                 ╚═════╝ ╚═════╝ ╚═╝  ╚═══╝   ╚═╝   ╚══════╝╚═╝  ╚═╝   ╚═╝

            ██████╗ ███████╗ ██████╗███████╗██╗██╗   ██╗███████╗██████╗ ███████╗
            ██╔══██╗██╔════╝██╔════╝██╔════╝██║██║   ██║██╔════╝██╔══██╗██╔════╝
            ██████╔╝█████╗  ██║     █████╗  ██║██║   ██║█████╗  ██████╔╝███████╗
            ██╔══██╗██╔══╝  ██║     ██╔══╝  ██║╚██╗ ██╔╝██╔══╝  ██╔══██╗╚════██║
            ██║  ██║███████╗╚██████╗███████╗██║ ╚████╔╝ ███████╗██║  ██║███████║
            ╚═╝  ╚═╝╚══════╝ ╚═════╝╚══════╝╚═╝  ╚═══╝  ╚══════╝╚═╝  ╚═╝╚══════╝


This session introduces context receivers, a new language feature available in Kotlin 1.6.20.
This session assumes prior knowledge about Kotlin in general and Kotlin extension receivers in particular.

A video recording of a training session for this tutorial can be found in our
confluence "Learning" page.

3rd party information:
https://github.com/Kotlin/KEEP/blob/master/proposals/context-receivers.md#context-receivers-and-contextual-declarations
https://proandroiddev.com/an-introduction-context-oriented-programming-in-kotlin-2e79d316b0a2
*/

