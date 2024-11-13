/* tslint:disable */
/* eslint-disable */
// Generated using typescript-generator version 3.2.1263 on 2024-11-13 11:47:55.

export interface PlayerStrategyDTO {
    playerChoice: Choice;
}

export interface RoundOutcomeDTO {
    playerChoice: Choice;
    computerChoice: Choice;
    outcome: Outcome;
}

export interface MetricCountDTO {
    computerCounts: { [P in Choice]?: number };
}

export interface MetricsDTO {
    totalCount: number;
    playerCounts: { [P in Choice]?: MetricCountDTO };
}

export interface PlayDTO {
    id: number;
    timestamp: Date;
    player: Choice;
    computer: Choice;
}

export type Choice = "ROCK" | "PAPER" | "SCISSORS";

export type Outcome = "PLAYER_WIN" | "DRAW" | "COMPUTER_WIN";
