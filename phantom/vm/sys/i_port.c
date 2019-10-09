/**
 *
 * Phantom OS
 *
 * Copyright (C) 2005-2019 Dmitry Zavalishin, dz@dz.ru
 *
 * .internal.port class implementation
 * 
 * See <https://github.com/dzavalishin/phantomuserland/wiki/InternalClasses>
 * See <https://github.com/dzavalishin/phantomuserland/wiki/InternalMethodWritingGuide>
 *
**/


#define DEBUG_MSG_PREFIX "vm.sysc.port"
#include <debug_ext.h>
#define debug_level_flow 0
#define debug_level_error 1
#define debug_level_info 0


#include <phantom_libc.h>
#include <vm/syscall_net.h>
#include <vm/alloc.h>
#include <kernel/snap_sync.h>
#include <kernel/net.h>
#include <kernel/json.h>

#include <errno.h>


static int debug_print = 0;





static int si_port_tostring_5( pvm_object_t me, pvm_object_t *ret, struct data_area_4_thread *tc, int n_args, pvm_object_t *args )
{
    (void) me;
    DEBUG_INFO;
    SYSCALL_RETURN( pvm_create_string_object( "port" ));
}








static int si_port_port_16( pvm_object_t me, pvm_object_t *ret, struct data_area_4_thread *tc, int n_args, pvm_object_t *args )
{
    (void) me;
    //struct data_area_4_udp      *da = pvm_data_area( me, udp );

    DEBUG_INFO;

    CHECK_PARAM_COUNT(0);
    //int addr = AS_INT(args[0]);
    //(void) addr;

    //SYSCALL_PUT_THIS_THREAD_ASLEEP()

    SYSCALL_THROW_STRING( "not implemented" );
}












syscall_func_t  syscall_table_4_port[18] =
{
    &si_void_0_construct,               &si_void_1_destruct,
    &si_void_2_class,                   &si_void_3_clone,
    &si_void_4_equals,                  &si_port_tostring_5,
    &si_void_6_toXML,                   &si_void_7_fromXML,
    // 8
    &invalid_syscall,                   &invalid_syscall,
    &invalid_syscall,                   &invalid_syscall,
    &invalid_syscall,                   &invalid_syscall,
    &invalid_syscall,                   &si_void_15_hashcode,
    // 16
    &si_port_port_16,                   &invalid_syscall,



};
DECLARE_SIZE(port);

















void pvm_internal_init_port(pvm_object_t os)
{
    struct data_area_4_port      *da = (struct data_area_4_port *)os->da;

    (void) da;

    //da->connected = 0;

}

pvm_object_t     pvm_create_port_object(void)
{
    return pvm_create_object( pvm_get_port_class() );
}

void pvm_gc_iter_port(gc_iterator_call_t func, pvm_object_t  os, void *arg)
{
    struct data_area_4_port      *da = (struct data_area_4_port *)os->da;

    (void) da;

    //gc_fcall( func, arg, ot );
    //gc_fcall( func, arg, da->p_kernel_porte_object );
    //gc_fcall( func, arg, da->callback );
}


void pvm_gc_finalizer_port( pvm_object_t  os )
{
    // is it called?
    struct data_area_4_port *da = (struct data_area_4_port *)os->da;
    (void) da;
}


void pvm_restart_port( pvm_object_t o )
{
    struct data_area_4_port *da = pvm_object_da( o, port );
    (void) da;
}

