#include <spinlock.h>
#include <hal.h>
#include <phantom_libc.h>

#if SPIN_DEBUG
#include <i386/proc_reg.h>
#endif

// -----------------------------------------------------------------------
// Spinlocks


#define	_spin_unlock(p) \
	({  register int _u__ ; \
	    __asm__ volatile("xorl %0, %0; \n\
			  xchgl %0, %1" \
			: "=&r" (_u__), "=m" (*(p)) ); \
	    0; })

// ret 1 on success
#define	_spin_try_lock(p)\
	(!({  register int _r__; \
	    __asm__ volatile("movl $1, %0; \n\
			  xchgl %0, %1" \
			: "=&r" (_r__), "=m" (*(p)) ); \
	    _r__; }))


#if SPIN_DEBUG
static int global_lock_entry_count = 0;
#endif


void hal_spin_init(hal_spinlock_t *sl)
{
    sl->lock = 0;
    sl->ebp = 0;
}

void hal_spin_lock(hal_spinlock_t *sl)
{
    if(hal_is_sti())
        printf("\n!spinlock STI!\n");

#if SPIN_DEBUG
    if(sl->lock)
    {
        printf("spinlock reenter detected, prev enter was here:\n");
        stack_dump_ebp((void *)sl->ebp);
        panic("reenter");
    }
#endif

    while( !  _spin_try_lock( &(sl->lock)  ) )
        while( sl->lock )
            ;


#if SPIN_DEBUG
    global_lock_entry_count++;
    sl->ebp = get_ebp();
#endif
}

void hal_spin_unlock(hal_spinlock_t *sl)
{
#if SPIN_DEBUG
    sl->ebp = 0;
    global_lock_entry_count--;
#endif
    _spin_unlock(&(sl->lock));

    if(hal_is_sti())
        printf("\n!spinunlock STI!\n");
}


void check_global_lock_entry_count()
{
#if SPIN_DEBUG && 0
    if(global_lock_entry_count)
    {
        printf("some spinunlock locked!");
        stack_dump_ebp( get_ebp() );
    }
#endif
}