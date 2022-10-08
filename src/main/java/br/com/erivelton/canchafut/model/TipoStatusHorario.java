package br.com.erivelton.canchafut.model;

public enum TipoStatusHorario {
	
	EM_ABERTO {
		@Override
		public void setarImagem(AgendamentoTimes agendaTime) {
			agendaTime.setImagem("aberto.png");
			agendaTime.setDesabilitado(false);
		}
	},
	
	CONFIRMADO {
		@Override
		public void setarImagem(AgendamentoTimes agendaTime) {
			agendaTime.setImagem("confirm.png");
		}
	},
	
	PARCIALMENTE_CONFIRMADO {
		@Override
		public void setarImagem(AgendamentoTimes agendaTime) {
			agendaTime.setImagem("parcial.png");
		}
	},
	
	CANCELADO {
		@Override
		public void setarImagem(AgendamentoTimes agendaTime) {
			agendaTime.setImagem("cancel-icon.png");
		}
	};

	public abstract void setarImagem(AgendamentoTimes agendaTime);
}
