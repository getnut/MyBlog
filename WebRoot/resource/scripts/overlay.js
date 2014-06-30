 var overlay = $('<div></div>')
      .addClass('overlay')
      .css({
        background: opts.color,
        opacity: opts.opacity,
        top: opts.container.toString() === 'body' ? $(opts.container).scrollTop() : $(opts.container).offset().top,
        left: $(opts.container).offset().left,
        width: opts.container === 'body' ? '100%' : $(opts.container).width(),
        height: opts.container === 'body' ? '100%' : $(opts.container).height(),
        position: 'absolute',
        zIndex: 1000,
        display: 'none',
        overflow: 'hidden'
      });