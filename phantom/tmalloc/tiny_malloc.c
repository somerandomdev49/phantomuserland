#include <user/tmalloc.h>
#include <stdio.h>

#define USED 1

static int inited = 0;

typedef struct {
    unsigned size;
} UNIT;

typedef struct {
    UNIT* free;
    UNIT* heap;
} MSYS;

static MSYS msys;

static UNIT* compact( UNIT *p, unsigned nsize )
{
    unsigned bsize, psize;
    UNIT *best;

    best = p;
    bsize = 0;

    while( psize = p->size, psize )
    {
        if( psize & USED )
        {
            if( bsize != 0 )
            {
                best->size = bsize;
                if( bsize >= nsize )
                {
                    return best;
                }
            }
            bsize = 0;
            best = p = (UNIT *)( (addr_t)p + (psize & ~USED) );
        }
        else
        {
            bsize += psize;
            p = (UNIT *)( (addr_t)p + psize );
        }
    }

    if( bsize != 0 )
    {
        best->size = bsize;
        if( bsize >= nsize )
        {
            return best;
        }
    }

    return 0;
}

void free( void *ptr )
{
    if(!inited) { printf("call init_malloc in main!\n"); exit(33); }
    if( ptr )
    {
        UNIT *p;

        p = (UNIT *)( (addr_t)ptr - sizeof(UNIT) );
        p->size &= ~USED;
    }
}

void *malloc( size_t size )
{
    if(!inited) { printf("call init_malloc in main!\n"); exit(33); }

    unsigned fsize;
    UNIT *p;

    if( size == 0 ) return 0;

    size  += 3 + sizeof(UNIT);
    size >>= 2;
    size <<= 2;

    if( msys.free == 0 || size > msys.free->size )
    {
        msys.free = compact( msys.heap, size );
        if( msys.free == 0 ) return 0;
    }

    p = msys.free;
    fsize = msys.free->size;

    if( fsize >= size + sizeof(UNIT) )
    {
        msys.free = (UNIT *)( (addr_t)p + size );
        msys.free->size = fsize - size;
    }
    else
    {
        msys.free = 0;
        size = fsize;
    }

    p->size = size | USED;

    return (void *)( (addr_t)p + sizeof(UNIT) );
}

void __init_malloc( void *heap, unsigned len )
{
    len  += 3;
    len >>= 2;
    len <<= 2;
    msys.free = msys.heap = (UNIT *) heap;
    msys.free->size = msys.heap->size = len - sizeof(UNIT);
    *(unsigned *)((char *)heap + len - 4) = 0;
    inited = 1;
}

/*
void MSYS_Compact( void )
{
    msys.free = compact( msys.heap, 0x7FFFFFFF );
}
*/
