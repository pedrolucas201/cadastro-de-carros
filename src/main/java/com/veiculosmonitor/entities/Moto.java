package com.veiculosmonitor.entities;

import javax.persistence.Entity;

@Entity
public class Moto extends Veiculo {
    private boolean temBauleto;

    // Getters e Setters

    public boolean isTemBauleto() {
        return temBauleto;
    }

    public void setTemBauleto(boolean temBauleto) {
        this.temBauleto = temBauleto;
    }
}
