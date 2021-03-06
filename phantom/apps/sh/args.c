//#include <types.h>
#include <string.h>
#include <ctype.h>
#include <stdio.h>

#include "args.h"
#include "shell_defs.h"
#include "shell_vars.h"

int af_exit_after_script;
char *af_script_file_name;


static void handle_option(char *option){

    switch(option[1]){
    case 's':// setup script
        if(option[2] == 0){
            af_exit_after_script = 0;
        }
        return;
    }

    printf("unknown option : %s\n",option);
}

void init_arguments(int argc,char **argv){

    int cnt = 1;
    int shell_argc = 0;
    char name[255];

    af_exit_after_script = 1;
    af_script_file_name  = NULL;

    while((cnt < argc) && (argv[cnt][0] == '-')){
        handle_option(argv[cnt]);
        cnt++;
    }

    if(cnt < argc)
    {

        af_script_file_name = argv[cnt];
        cnt++;

        while(cnt < argc){

            snprintf(name, sizeof(name),NAME_PAR_PRFX,cnt-1);
            shell_var_set_text(name,argv[cnt]);
            shell_argc++;
            cnt++;
        }

        shell_var_set_number(NAME_VAR_ARGC,shell_argc);

    }

    if( af_script_file_name == 0 )        
    {
        af_script_file_name = "/amnt0/etc/.profile";
        //af_script_file_name = "/amnt0/etc/rc";
        af_exit_after_script = 0;
    }
}
